package com.example.chucknorrisgenerator;

import com.example.chucknorrisgenerator.Entities.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeService {
    @GET("jokes/random?category=dev")
    Call<Joke> getJoke();
}
