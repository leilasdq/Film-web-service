package com.example.filmwebservices.model;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("current_page")
	private String currentPage;

	@SerializedName("page_count")
	private int pageCount;

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage = currentPage;
	}

	public String getCurrentPage(){
		return currentPage;
	}

	public void setPageCount(int pageCount){
		this.pageCount = pageCount;
	}

	public int getPageCount(){
		return pageCount;
	}

	@Override
 	public String toString(){
		return 
			"Metadata{" + 
			"per_page = '" + perPage + '\'' + 
			",total_count = '" + totalCount + '\'' + 
			",current_page = '" + currentPage + '\'' + 
			",page_count = '" + pageCount + '\'' + 
			"}";
		}
}