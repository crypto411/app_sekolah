package com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.Resource;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.local.UserPreferences;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.RemoteDataSource;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Mapel;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Nilai;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Siswa;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;

@HiltViewModel
public class AdminViewModel extends ViewModel{
    private final RemoteDataSource remoteDataSource;
    private final UserPreferences userPreferences;

    @Inject
    AdminViewModel(RemoteDataSource remoteDataSource, UserPreferences userPreferences) {
        this.remoteDataSource = remoteDataSource;
        this.userPreferences = userPreferences;
    }

    // Mapel
    public Flowable<List<Mapel>> getAllMapel() {
        return remoteDataSource.getAllMapel();
    }
    public Flowable<String> createMapel(String kdMapel, String namaMapel) {
        return remoteDataSource.createMapel(kdMapel, namaMapel);
    }
    public Flowable<String> deleteMapel(String kdMapel) {
        return remoteDataSource.deleteMapel(kdMapel);
    }

    // Siswa
    public Flowable<List<Siswa>> getAllSiswa() {
        return remoteDataSource.getAllSiswa();
    }
    public Flowable<String> createSiswa(String nis, String name, String address, String gender) {
        return remoteDataSource.createSiswa(nis, name, address, gender);
    }
    public Flowable<String> deleteSiswa(String nis) {
        return remoteDataSource.deleteSiswa(nis);
    }

    // Nilai
    public Flowable<Nilai> getNilai(String nis, String kdMapel) {
        return remoteDataSource.getNilai(nis, kdMapel);
    }

    public Flowable<String> updateNilai(String nis, String kdMapel, String nilai) {
        return remoteDataSource.updateNilai(nis, kdMapel, nilai);
    }

    // Session
    public Flowable<User> getSession() {
        return userPreferences.get();
    }
    public LiveData<Boolean> clearSession() {
        return userPreferences.clear();
    }
}
