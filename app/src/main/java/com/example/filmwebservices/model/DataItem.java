package com.example.filmwebservices.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataItem{

	@SerializedName("country")
	private String country;

	@SerializedName("images")
	private List<String> images;

	@SerializedName("year")
	private String year;

	@SerializedName("genres")
	private List<String> genres;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("imdb_rating")
	private String imdbRating;

	@SerializedName("poster")
	private String poster;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setGenres(List<String> genres){
		this.genres = genres;
	}

	public List<String> getGenres(){
		return genres;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setImdbRating(String imdbRating){
		this.imdbRating = imdbRating;
	}

	public String getImdbRating(){
		return imdbRating;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}

	public String getPoster(){
		return poster;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"country = '" + country + '\'' + 
			",images = '" + images + '\'' + 
			",year = '" + year + '\'' + 
			",genres = '" + genres + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",imdb_rating = '" + imdbRating + '\'' + 
			",poster = '" + poster + '\'' + 
			"}";
		}
}