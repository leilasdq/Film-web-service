package com.example.filmwebservices.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmwebservices.R;
import com.example.filmwebservices.databinding.FragmentMoviesBinding;
import com.example.filmwebservices.model.DataItem;
import com.example.filmwebservices.network.MovieFetcher;

import java.util.ArrayList;
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
               mAdapter = new FilmsAdapter(mList);
               mBinding.filmsList.setAdapter(mAdapter);
           }
       });

        return mBinding.getRoot();
    }

    private class FilmsViewHolder extends RecyclerView.ViewHolder{
        DataItem mDataItem;
        ImageView mPoster;
        TextView mName;
        TextView mGener;
        TextView mYear;
        TextView mImdb;

        public FilmsViewHolder(@NonNull View itemView) {
            super(itemView);

            mPoster = itemView.findViewById(R.id.film_image);
            mName = itemView.findViewById(R.id.name);
            mGener = itemView.findViewById(R.id.gener);
            mYear = itemView.findViewById(R.id.year);
            mImdb = itemView.findViewById(R.id.imdb_name);
        }

        public void bind(DataItem dataItem){
            mDataItem = dataItem;

            mName.setText(mDataItem.getTitle());
            mGener.setText(mDataItem.getGenres().get(0));
            mYear.setText(mDataItem.getYear());
            mImdb.setText(mDataItem.getImdbRating());
        }
    }

    private class FilmsAdapter extends RecyclerView.Adapter<FilmsViewHolder>{

        private List<DataItem> mItemList = new ArrayList<>();

        public FilmsAdapter(List<DataItem> itemList) {
            mItemList = itemList;
        }

        @NonNull
        @Override
        public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.films_list_items, parent, false);
            return new FilmsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FilmsViewHolder holder, int position) {
            holder.bind(mItemList.get(position));
        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }
    }

}
