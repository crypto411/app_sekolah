package com.user.fadhlanhadaina.app_sekolah.core.data.source.local;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.user.fadhlanhadaina.app_sekolah.core.domain.model.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

public class UserPreferences {
    public RxDataStore<Preferences> dataStore;

    @Inject
    UserPreferences(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    public Flowable<User> get() {
        return dataStore.data().map(prefs -> new User(prefs.get(KEY_USERID), prefs.get(KEY_USERNAME), null, prefs.get(KEY_ROLE), prefs.get(KEY_NIS)));
    }

    public void store(User user) {
        dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences _prefsIn = prefsIn.toMutablePreferences();
            _prefsIn.set(KEY_USERID, user.userId);
            _prefsIn.set(KEY_USERNAME, user.username);
            _prefsIn.set(KEY_ROLE, user.role);
            _prefsIn.set(KEY_NIS, user.nis);
            return Single.just(_prefsIn);
        });
    }

    public LiveData<Boolean> clear() {
        MutableLiveData<Boolean> liveData = new MutableLiveData<Boolean>();
        dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences _prefsIn = prefsIn.toMutablePreferences();
            _prefsIn.clear();
            return Single.just(_prefsIn);
        }).subscribe(success -> liveData.postValue(true), error -> liveData.postValue(false));
        return liveData;
    }

    public static final Preferences.Key<String> KEY_USERID = PreferencesKeys.stringKey("key_userid");
    public static final Preferences.Key<String> KEY_USERNAME = PreferencesKeys.stringKey("key_username");
    public static final Preferences.Key<String> KEY_ROLE = PreferencesKeys.stringKey("key_role");
    public static final Preferences.Key<String> KEY_NIS = PreferencesKeys.stringKey("key_nis");
}
