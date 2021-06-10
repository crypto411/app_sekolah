package com.user.fadhlanhadaina.app_sekolah.core.domain.model;

public class Siswa {
    private String nis;
    private String nama;
    private String alamat;
    private String jenKel;

    public Siswa(String nis, String nama, String alamat, String jenKel) {
        this.nis = nis;
        this.nama = nama;
        this.alamat = alamat;
        this.jenKel = jenKel;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getJenKel() {
        return jenKel;
    }

    public void setJenKel(String jenKel) {
        this.jenKel = jenKel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
