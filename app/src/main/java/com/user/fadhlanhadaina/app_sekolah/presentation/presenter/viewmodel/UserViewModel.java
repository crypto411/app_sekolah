package com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.local.UserPreferences;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.RemoteDataSource;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Nilai;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Siswa;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;

@HiltViewModel
public class UserViewModel extends ViewModel{
    private final RemoteDataSource remoteDataSource;
    private final UserPreferences userPreferences;

    @Inject
    UserViewModel(RemoteDataSource remoteDataSource, UserPreferences userPreferences) {
        this.remoteDataSource = remoteDataSource;
        this.userPreferences = userPreferences;
    }

    // User
    public Flowable<List<Nilai>> getNilaiSiswa(String nis) {
        return remoteDataSource.getNilaiSiswa(nis);
    }
    public Flowable<Siswa> getSiswa(String nis) {
        return remoteDataSource.getSiswa(nis);
    }

    // Session
    public Flowable<User> getSession() {
        return userPreferences.get();
    }
    public LiveData<Boolean> clearSession() {
        return userPreferences.clear();
    }

}
