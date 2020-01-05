package com.example.filmwebservices.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmwebservices.databinding.FilmsListItemsBinding;
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
        LayoutInflater inflater = LayoutInflater.from(mContext);
        FilmsListItemsBinding filmsListItemsBinding = FilmsListItemsBinding.inflate(inflater, parent, false);
        return new FilmsViewHolder(filmsListItemsBinding);
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
        FilmsListItemsBinding mBinding;

        public FilmsViewHolder(FilmsListItemsBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public void bind(DataItem dataItem){
            mDataItem = dataItem;

            mBinding.name.setText(mDataItem.getTitle());
            mBinding.gener.setText(mDataItem.getGenres().get(0));
            mBinding.year.setText(mDataItem.getYear());
            mBinding.imdbName.setText(mDataItem.getImdbRating());
            Glide.with(mContext).load(mDataItem.getPoster()).centerCrop().into(mBinding.filmImage);
        }
    }
}
