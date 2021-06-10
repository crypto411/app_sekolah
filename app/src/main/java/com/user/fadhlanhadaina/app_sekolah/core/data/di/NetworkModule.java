package com.user.fadhlanhadaina.app_sekolah.core.data.di;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.RemoteDataSource;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network.AdminService;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network.AuthService;
import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.network.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.user.fadhlanhadaina.app_sekolah.core.util.Constant.BASE_URL;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @Singleton
    @Provides
    public AuthService provideAuthClientApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService.class);
    }

    @Singleton
    @Provides
    public AdminService provideAdminClientApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(AdminService.class);
    }

    @Singleton
    @Provides
    public UserService provideUserClientApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(UserService.class);
    }

    @Singleton
    @Provides
    public RemoteDataSource provideRemoteDataSource(AuthService authService, AdminService adminService, UserService userService) {
        return RemoteDataSource.newInstance(authService, adminService, userService);
    }
}
