package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.MapelRecordsResponse;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.MapelResponse;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.NilaiResponse;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.NilaiRecordsResponse;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.SiswaRecordsResponse;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.SiswaResponse;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.StatusResponse;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AdminService {

    //===== Matapelajaran ========
    @GET("admin/mapel/retrieve.php")
    Flowable<MapelRecordsResponse> getAllMapel();

    @GET("admin/mapel/getupdate.php")
    Flowable<MapelResponse> getMapel(@Query("kdmapel") String kdMapel);

    @GET("admin/mapel/create.php")
    Flowable<StatusResponse> addMapel(@Query("kdmapel") String kdMapel, @Query("namamapel") String namaMapel);

    @GET("admin/mapel/delete.php")
    Flowable<StatusResponse> removeMapel(@Query("kdmapel") String kdMapel);
    //=============================

    //===== Siswa ========
    @GET("admin/siswa/retrieve.php")
    Flowable<SiswaRecordsResponse> getAllSiswa();

    @GET("admin/siswa/getupdate.php")
    Flowable<SiswaResponse> getSiswa(@Query("kdmapel") String kdMapel);

    @GET("admin/siswa/create.php")
    Flowable<StatusResponse> addSiswa(
        @Query("nis") String nis,
        @Query("nama") String name,
        @Query("alamat") String address,
        @Query("jen_kel") String gender);

    @GET("admin/siswa/delete.php")
    Flowable<StatusResponse> removeSiswa(@Query("nis") String nis);
    //=============================

    //===== Nilai =================
    @GET("admin/nilai/update.php")
    Flowable<StatusResponse> updateNilai(@Query("nis") String nis, @Query("kdmapel") String kdMapel, @Query("nilai") String nilai);

    @GET("admin/nilai/getupdate.php")
    Flowable<NilaiResponse> getNilai(@Query("nis") String nis, @Query("kdmapel") String kdMapel);
    //=============================
}
