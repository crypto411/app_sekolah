package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class StatusResponse {

	@SerializedName("status")
	private String status;

	public String getStatus(){
		return status;
	}
}