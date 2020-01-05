package com.example.filmwebservices.network;

import com.example.filmwebservices.model.FilmObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Interfaces {

    @GET("movies")
    Call<FilmObject> getMovies(@QueryMap Map<String, String> queries);
}
