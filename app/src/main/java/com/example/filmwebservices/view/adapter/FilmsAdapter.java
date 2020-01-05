package com.example.filmwebservices.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmwebservices.R;
import com.example.filmwebservices.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>{

    private List<DataItem> mItemList = new ArrayList<>();
    private Context mContext;

    public FilmsAdapter(List<DataItem> itemList, Context context) {
        mItemList = itemList;
        mContext = context;
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.films_list_items, parent, false);
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

    class FilmsViewHolder extends RecyclerView.ViewHolder{
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
            Glide.with(mContext).load(mDataItem.getPoster()).centerCrop().into(mPoster);
        }
    }
}
