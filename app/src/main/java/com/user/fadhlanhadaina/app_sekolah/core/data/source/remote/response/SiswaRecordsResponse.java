package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SiswaRecordsResponse {

	@SerializedName("records")
	private List<SiswaResponse> records;

	public List<SiswaResponse> getRecords(){
		return records;
	}
}