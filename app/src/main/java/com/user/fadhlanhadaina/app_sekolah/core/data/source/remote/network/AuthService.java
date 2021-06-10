package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthService {

    @GET("login.php")
    Call<LoginResponse> login(@Query("name") String name, @Query("pass") String password);
}
