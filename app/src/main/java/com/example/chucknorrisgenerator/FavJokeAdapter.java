package com.example.chucknorrisgenerator;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chucknorrisgenerator.Entities.Joke;

import java.util.List;

public class FavJokeAdapter extends RecyclerView.Adapter<FavJokeAdapter.ListViewHolder> {
    private static final String TAG = "AdapterList";
    private final List<Joke> mList;
    private LayoutInflater mInflater;

    public FavJokeAdapter(Context context, List<Joke> list){
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.jokelist_item, parent, false);
        return new ListViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        Joke mCurrent = mList.get(position);

        String joke = mCurrent.getValue();

        holder.joke.setText(joke);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder class
    public class ListViewHolder extends RecyclerView.ViewHolder{

        public final TextView joke;
        final FavJokeAdapter mAdapter;

        public ListViewHolder(@NonNull View itemView, FavJokeAdapter adapter) {
            super(itemView);
            joke =itemView.findViewById(R.id.jokeListText);
            this.mAdapter = adapter;
        }

    }
}
