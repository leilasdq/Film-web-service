package com.example.filmwebservices.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.filmwebservices.R;
import com.example.filmwebservices.databinding.FragmentMoviesBinding;
import com.example.filmwebservices.model.DataItem;
import com.example.filmwebservices.network.MovieFetcher;
import com.example.filmwebservices.view.adapter.FilmsAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private MovieFetcher mMovieFetcher;
    private FragmentMoviesBinding mBinding;
    private MutableLiveData<List<DataItem>> mLiveData = new MutableLiveData<>();
    private FilmsAdapter mAdapter;
    private List<DataItem> mList;

    public MoviesFragment() {
        // Required empty public constructor
    }

    public static MoviesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieFetcher = MovieFetcher.getInstance();
//        mLiveData = mMovieFetcher.getResponseMutableLiveData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mBinding  = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

       mBinding.filmsList.setLayoutManager(new LinearLayoutManager(getActivity()));
       mLiveData = mMovieFetcher.getMovies(1);

        mLiveData.observe(this, new Observer<List<DataItem>>() {
           @Override
           public void onChanged(List<DataItem> dataItems) {
               mList = mLiveData.getValue();
               mAdapter = new FilmsAdapter(mList, getContext());
               mBinding.filmsList.setAdapter(mAdapter);
           }
       });

        return mBinding.getRoot();
    }

}
