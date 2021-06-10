package com.user.fadhlanhadaina.app_sekolah.core.domain.model;

public class Mapel {

    private String kdMapel;
    private String namaMapel;

    public Mapel(String kdMapel, String namaMapel) {
        this.kdMapel = kdMapel;
        this.namaMapel = namaMapel;
    }

    public String getKdMapel() {
        return kdMapel;
    }

    public void setKdMapel(String kdMapel) {
        this.kdMapel = kdMapel;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }
}
