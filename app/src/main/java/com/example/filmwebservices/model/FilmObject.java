package com.example.filmwebservices.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmObject {

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("data")
	private List<DataItem> data;

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"FilmObject{" +
			"metadata = '" + metadata + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}