package com.user.fadhlanhadaina.app_sekolah.core.domain.model;



public class Nilai{
    private String nis;
    private String kdMapel;
    private String namaMapel;
    private String nilai;

    public Nilai(String nis, String kdMapel, String namaMapel, String nilai) {
        this.nis = nis;
        this.kdMapel = kdMapel;
        this.namaMapel = namaMapel;
        this.nilai = nilai;
    }

    public Nilai(String nis, String kdMapel, String nilai) {
        this.nis = nis;
        this.kdMapel = kdMapel;
        this.nilai = nilai;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
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

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}