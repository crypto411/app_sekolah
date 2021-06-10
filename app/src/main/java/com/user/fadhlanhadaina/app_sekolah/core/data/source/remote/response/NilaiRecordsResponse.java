package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NilaiRecordsResponse{

	@SerializedName("records")
	private List<NilaiResponse> records;

	public List<NilaiResponse> getRecords(){
		return records;
	}
}