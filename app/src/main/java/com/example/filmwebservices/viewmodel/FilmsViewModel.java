package com.example.filmwebservices.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.filmwebservices.model.DataItem;
import com.example.filmwebservices.network.MovieFetcher;

import java.util.List;

public class FilmsViewModel extends AndroidViewModel {
    private MovieFetcher mFetcher;
    private LiveData<List<DataItem>> mItemsLiveData;

    public FilmsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<DataItem>> getItemsLiveData() {
        mFetcher = MovieFetcher.getInstance();
        mItemsLiveData = mFetcher.getMovies(1);
        return mItemsLiveData;
    }
}
