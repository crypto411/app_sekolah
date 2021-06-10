package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class SiswaResponse {

	@SerializedName("nama")
	private String nama;

	@SerializedName("nis")
	private String nis;

	@SerializedName("jen_kel")
	private String jenKel;

	@SerializedName("alamat")
	private String alamat;

	public String getNama(){
		return nama;
	}

	public String getNis(){
		return nis;
	}

	public String getJenKel(){
		return jenKel;
	}

	public String getAlamat(){
		return alamat;
	}
}