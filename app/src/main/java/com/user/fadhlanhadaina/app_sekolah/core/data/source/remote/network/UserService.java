package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.NilaiRecordsResponse;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.SiswaResponse;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("retrieve-nilai-data.php")
    Flowable<NilaiRecordsResponse> getNilaiSiswa(@Query("nis") String nis);

    @GET("retrieve-siswa-data.php")
    Flowable<SiswaResponse> getSiswa(@Query("nis") String nis);
}
