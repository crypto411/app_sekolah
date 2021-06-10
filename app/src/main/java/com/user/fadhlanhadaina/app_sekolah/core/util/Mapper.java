package com.user.fadhlanhadaina.app_sekolah.core.util;

import com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response.LoginResponse;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.User;

public class Mapper {
    public static User mapLoginResponseToUser(LoginResponse loginResponse) {
        return new User(loginResponse.userId, loginResponse.username, loginResponse.password, loginResponse.role, loginResponse.nis);
    }
}
