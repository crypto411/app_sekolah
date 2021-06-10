package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class NilaiResponse {

	@SerializedName("nilai")
	private String nilai;

	@SerializedName("kdmapel")
	private String kdMapel;

	@SerializedName("namamapel")
	private String namaMapel;

	@SerializedName("nis")
	private String nis;

	public String getNilai() {
		return nilai;
	}

	public String getKdMapel() {
		return kdMapel;
	}

	public String getNamaMapel() {
		return namaMapel;
	}

	public String getNis() {
		return nis;
	}
}