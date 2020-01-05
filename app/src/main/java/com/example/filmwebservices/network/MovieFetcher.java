package com.example.filmwebservices.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.filmwebservices.model.DataItem;
import com.example.filmwebservices.model.FilmObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFetcher {
    public static final String BASE_URL = "http://moviesapi.ir/api/v1/";
    private static final String TAG = "MovieFetcher";

    private Interfaces mInterfaces;
    private Retrofit mRetrofit;
    private static MovieFetcher sInstance;
    private Map<String, String> mQueries = new HashMap<>();

    private MutableLiveData<List<DataItem>> mDataMutableLiveData = new MutableLiveData<>();

    private MovieFetcher() {
        mRetrofit =  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mInterfaces = mRetrofit.create(Interfaces.class);
    }

    public static MovieFetcher getInstance() {
        if (sInstance==null){
            sInstance = new MovieFetcher();
        }
        return sInstance;
    }

    public MutableLiveData<List<DataItem>> getMovies(int pageNum) {
        mQueries.put("page", String.valueOf(pageNum));
        Call<FilmObject> call = mInterfaces.getMovies(mQueries);

        call.enqueue(new Callback<FilmObject>() {
            @Override
            public void onResponse(Call<FilmObject> call, Response<FilmObject> response) {
                if (response.isSuccessful()){
                    FilmObject filmObject = response.body();
                    List<DataItem> dataItemList = filmObject.getData();

                    mDataMutableLiveData.setValue(dataItemList);
                }
            }

            @Override
            public void onFailure(Call<FilmObject> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

        return mDataMutableLiveData;
    }

    public MutableLiveData<List<DataItem>> getResponseMutableLiveData() {
        return mDataMutableLiveData;
    }
}
