package com.example.chucknorrisgenerator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chucknorrisgenerator.Entities.Joke;

import java.util.ArrayList;
import java.util.List;


public class FavouriteJokes extends Fragment {

    private RecyclerView jokeList;
    private FavJokeAdapter rAdapter;
    private List<Joke> jokes = new ArrayList<>();

    public FavouriteJokes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!(Joke.getFavouriteJokes()==null)){
            jokes = Joke.getFavouriteJokes();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Set instructions text
        String instruction1 = "- View your favourite jokes";
        String instruction2 = "- Swipe left/right to remove a joke";

        MainActivity mainActivity = (MainActivity) getActivity();

        TextView display1 = mainActivity.display1;
        TextView display2 = mainActivity.display2;
        display1.setText(instruction1);
        display2.setText(instruction2);

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_favourite_jokes, container, false);

        //Find the Recycler view widget
        jokeList = rootview.findViewById(R.id.favJokeList);

        //Create a new adapter linked to stored joke list
        rAdapter = new FavJokeAdapter(rootview.getContext(), jokes);

        //Set the adapter to the Recycler View
        jokeList.setAdapter(rAdapter);

        //Set a default layout manager to the joke list view
        jokeList.setLayoutManager(new LinearLayoutManager(rootview.getContext()));

        //Create a simple call back for enabling swipe to delete feature
        ItemTouchHelper.SimpleCallback removeJokeFromList = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView rV, RecyclerView.ViewHolder vH, RecyclerView.ViewHolder target) {
                Toast.makeText(rootview.getContext(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder vH, int swipeDirection) {
                final int position = vH.getAdapterPosition();
                Joke.removeFromFavouriteJokes(position);
                rAdapter.notifyDataSetChanged();
            }
        };

        //Initialize the ItemTouchHelper and attach recycler view
        ItemTouchHelper touchHelper = new ItemTouchHelper(removeJokeFromList);
        touchHelper.attachToRecyclerView(jokeList);

        return rootview;
    }
}
