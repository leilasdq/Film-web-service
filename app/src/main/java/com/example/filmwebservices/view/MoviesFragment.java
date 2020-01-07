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
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmwebservices.R;
import com.example.filmwebservices.databinding.FragmentMoviesBinding;
import com.example.filmwebservices.model.DataItem;
import com.example.filmwebservices.view.adapter.EndlessRecyclerView;
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
    private LinearLayoutManager manager;
    private EndlessRecyclerView scrollListener;
    private int pageNumber = 1;

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
        mViewModel.sendRequest(pageNumber);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        manager = new LinearLayoutManager(getActivity());
        mBinding.filmsList.setLayoutManager(manager);
        scrollListener = new EndlessRecyclerView(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                pageNumber++;
                mViewModel.sendRequest(pageNumber);
            }
        };
        mBinding.filmsList.addOnScrollListener(scrollListener);

        return mBinding.getRoot();
    }

    private void setupAdapter(List<DataItem> items) {
        if (mAdapter == null) {
            mAdapter = new FilmsAdapter(items, getContext());
            mBinding.filmsList.setAdapter(mAdapter);
        } else {
            mAdapter.setItemList(items);
            mAdapter.notifyDataSetChanged();
        }
    }

}
