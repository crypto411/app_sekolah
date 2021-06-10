package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class MapelResponse {

	@SerializedName("kdmapel")
	private String kdmapel;

	@SerializedName("namamapel")
	private String namamapel;

	public String getKdmapel(){
		return kdmapel;
	}

	public String getNamamapel(){
		return namamapel;
	}
}