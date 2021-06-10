package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("userid")
    public String userId;
    public String username;
    public String password;
    public String role;
    public String nis;
}
