package com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.Resource;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.local.UserPreferences;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.RemoteDataSource;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.User;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final RemoteDataSource remoteDataSource;
    private final UserPreferences userPreferences;

    @Inject
    LoginViewModel(RemoteDataSource remoteDataSource, UserPreferences userPreferences) {
        this.remoteDataSource = remoteDataSource;
        this.userPreferences = userPreferences;
    }

    public LiveData<Resource<User>> login(String name, String password) {
        return remoteDataSource.login(name, password);
    }

    public void storeSession(User user) {
        userPreferences.store(user);
    }

    public Flowable<User> getSession() {
        return userPreferences.get();
    }
}
