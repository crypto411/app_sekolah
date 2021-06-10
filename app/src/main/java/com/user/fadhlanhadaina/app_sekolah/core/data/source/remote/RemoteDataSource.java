package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.Resource;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network.AdminService;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network.AuthService;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network.UserService;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.LoginResponse;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Mapel;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Nilai;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Siswa;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.User;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.user.fadhlanhadaina.app_sekolah.core.util.Mapper.mapLoginResponseToUser;

public class RemoteDataSource {
    private AuthService authService;
    private AdminService adminService;
    private UserService userService;

    public RemoteDataSource(AuthService authService, AdminService adminService, UserService userService) {
        this.authService = authService;
        this.adminService = adminService;
        this.userService = userService;
    }

    public static RemoteDataSource newInstance(AuthService authService, AdminService adminService, UserService userService) {
        return new RemoteDataSource(authService, adminService, userService);
    }

    // Auth
    public LiveData<Resource<User>> login(String name, String pass) {
        MutableLiveData<Resource<User>> mutableLiveData = new MutableLiveData<>();
        authService.login(name, pass).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    mutableLiveData.postValue(new Resource<User>(mapLoginResponseToUser(loginResponse) , null));
                }
                else {
                    mutableLiveData.postValue(new Resource<User>(null, response.code()+" "+response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mutableLiveData.postValue(new Resource<>(null, t.getMessage()));
            }
        });
        return mutableLiveData;
    }

    // Admin
    //-- Mapel
    public Flowable<List<Mapel>> getAllMapel() {
        return Flowable.create(emitter -> adminService.getAllMapel().subscribe(next -> {
            emitter.onNext(next.getRecords().stream().map(it ->
                new Mapel(it.getKdmapel(), it.getNamamapel())
            ).collect(Collectors.toList()));
        },
        emitter::onError,
        emitter::onComplete), BackpressureStrategy.BUFFER);
    }

    public Flowable<String> createMapel(String kdMapel, String namaMapel) {
        return Flowable.create(emitter -> {
            adminService.addMapel(kdMapel, namaMapel).subscribe(
                next -> {
                    emitter.onNext(next.getStatus());
                    Log.d("Ogeee", next.getStatus());
                },
                emitter::onError,
                emitter::onComplete
            );
        }, BackpressureStrategy.BUFFER);
    }

    public Flowable<String> deleteMapel(String kdMapel) {
        return Flowable.create(emitter -> {
            adminService.removeMapel(kdMapel).subscribe(
                next -> {
                    emitter.onNext(next.getStatus());
                },
                emitter::onError,
                emitter::onComplete
            );
        }, BackpressureStrategy.BUFFER);
    }
    //---

    //--- Siswa
    public Flowable<List<Siswa>> getAllSiswa() {
        return Flowable.create(emitter -> adminService.getAllSiswa().subscribe(next -> {
            emitter.onNext(next.getRecords().stream().map(it ->
                new Siswa(it.getNis(), it.getNama(), it.getAlamat(), it.getJenKel())).collect(Collectors.toList())
            );
        },
        emitter::onError,
        emitter::onComplete), BackpressureStrategy.BUFFER);
    }

    public Flowable<String> createSiswa(String nis, String name, String address, String gender) {
        return Flowable.create(emitter -> {
            adminService.addSiswa(nis, name, address, gender).subscribe(
                    next -> {
                        emitter.onNext(next.getStatus());
                    },
                    emitter::onError,
                    emitter::onComplete
            );
        }, BackpressureStrategy.BUFFER);
    }

    public Flowable<String> deleteSiswa(String nis) {
        return Flowable.create(emitter -> {
            adminService.removeSiswa(nis).subscribe(
                next -> {
                    emitter.onNext(next.getStatus());
                },
                emitter::onError,
                emitter::onComplete
            );
        }, BackpressureStrategy.BUFFER);
    }
    //---

    //--- Nilai
    public Flowable<Nilai> getNilai(String nis, String kdMapel) {
        return Flowable.create(emitter -> {
            adminService.getNilai(nis, kdMapel).subscribe(
                next -> emitter.onNext(
                    new Nilai(next.getNis(), next.getKdMapel(), next.getNilai())
                ),
                emitter::onError,
                emitter::onComplete
            );
        }, BackpressureStrategy.BUFFER);
    }

    public Flowable<String> updateNilai(String nis, String kdMapel, String nilai) {
        return Flowable.create(emitter -> {
            adminService.updateNilai(nis, kdMapel, nilai).subscribe(
                next -> emitter.onNext(next.getStatus()),
                emitter::onError,
                emitter::onComplete
            );
        }, BackpressureStrategy.BUFFER);
    }
    //---

    // User
    public Flowable<List<Nilai>> getNilaiSiswa(String nis) {
        return Flowable.create(emitter ->
            userService.getNilaiSiswa(nis).subscribe(
                next -> {
                    emitter.onNext(
                        next.getRecords().stream().map(it ->
                            new Nilai(it.getNis(), it.getKdMapel(), it.getNamaMapel(), it.getNilai())
                        ).collect(Collectors.toList())
                    );
                },
                emitter::onError,
                emitter::onComplete
            )
        , BackpressureStrategy.BUFFER);
    }

    public Flowable<Siswa> getSiswa(String nis) {
        return Flowable.create(emitter ->
            userService.getSiswa(nis).subscribe(
                next ->
                    emitter.onNext(
                        new Siswa(next.getNis(), next.getNama(), next.getAlamat(), next.getJenKel())
                    )
                ,
                emitter::onError,
                emitter::onComplete
            )
        , BackpressureStrategy.BUFFER);
    }
}
