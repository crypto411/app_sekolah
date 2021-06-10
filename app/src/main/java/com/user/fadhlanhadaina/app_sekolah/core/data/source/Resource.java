package com.user.fadhlanhadaina.app_sekolah.core.data.source;

public class Resource<U> {
    public U data;
    public String message;

    public Resource(U data, String message) {
        this.data = data;
        this.message = message;
    }
}
