package com.user.fadhlanhadaina.app_sekolah.core.data.source.remote.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MapelRecordsResponse {

	@SerializedName("records")
	private List<MapelResponse> records;

	public List<MapelResponse> getRecords(){
		return records;
	}
}