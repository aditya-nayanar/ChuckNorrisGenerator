package com.example.chucknorrisgenerator;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chucknorrisgenerator.Entities.Joke;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GenerateJoke extends Fragment {

    Joke currentJoke;

    public GenerateJoke() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_generate_joke, container, false);

        String instruction1 = "- Click Generate to display a random dev joke";
        String instruction2 = "- Add any joke to your favourites list";

        MainActivity mainActivity = (MainActivity) getActivity();

        TextView display1 = mainActivity.display1;
        TextView display2 = mainActivity.display2;
        display1.setText(instruction1);
        display2.setText(instruction2);

        rootView.findViewById(R.id.bAddToFav).setVisibility(View.INVISIBLE);

        Button bGenerate = rootView.findViewById(R.id.bGenerate);
        final TextView jokeText = rootView.findViewById(R.id.jokeText);
        bGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomJoke(jokeText, rootView);
            }
        });

        Button bAddToFav = rootView.findViewById(R.id.bAddToFav);
        bAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joke.addToFavouriteJokes(currentJoke);
                Toast.makeText(rootView.getContext(), "Joke added to favourites", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public void generateRandomJoke(final TextView text, View view){

        view.findViewById(R.id.bAddToFav).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.bGenerate).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.loadingLabel).setVisibility(View.VISIBLE);

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JokeService js = rf.create(JokeService.class);

        final Call<Joke> coinListCall = js.getJoke();
        coinListCall.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response){
                if(response.isSuccessful()){
                    Joke joke = response.body();
                    currentJoke = joke;
                    System.out.println(joke.getValue());
                    text.setText(joke.getValue());
                    view.findViewById(R.id.loadingLabel).setVisibility(View.INVISIBLE);
                    view.findViewById(R.id.bAddToFav).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.bGenerate).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t){

            }
        });

    }

}


