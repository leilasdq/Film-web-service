package com.example.filmwebservices.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.filmwebservices.R;
import com.example.filmwebservices.databinding.FragmentMoviesBinding;
import com.example.filmwebservices.model.DataItem;
import com.example.filmwebservices.view.adapter.FilmsAdapter;
import com.example.filmwebservices.viewmodel.FilmsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private FilmsViewModel mViewModel;
    private FragmentMoviesBinding mBinding;
    private FilmsAdapter mAdapter;

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

        mViewModel = ViewModelProviders.of(this).get(FilmsViewModel.class);
        mViewModel.getItemsLiveData().observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                setupAdapter(dataItems);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mBinding  = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        mBinding.filmsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return mBinding.getRoot();
    }

    public void setupAdapter(List<DataItem> items) {
        if (mAdapter == null) {
            mAdapter = new FilmsAdapter(items, getContext());
            mBinding.filmsList.setAdapter(mAdapter);
        } else {
            mAdapter.setItemList(items);
            mAdapter.notifyDataSetChanged();
        }
    }

}
