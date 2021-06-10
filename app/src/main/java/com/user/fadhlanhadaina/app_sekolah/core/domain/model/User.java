package com.user.fadhlanhadaina.app_sekolah.core.domain.model;

public class User {
    public String userId;
    public String username;
    public String password;
    public String role;
    public String nis;

    public User(String userId, String username, String password, String role, String nis) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.nis = nis;
    }
}
