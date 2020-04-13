package com.example.chucknorrisgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display1;
    TextView display2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display1 = findViewById(R.id.instruction1);
        display2 = findViewById(R.id.instruction2);

        GenerateJoke fragment = new GenerateJoke();
        FragmentManager myManager = getSupportFragmentManager();
        FragmentTransaction myTransaction = myManager.beginTransaction();
        myTransaction.replace(R.id.mainScreen, fragment);
        myTransaction.commit();

        Button bRandom = findViewById(R.id.bRandom);
        bRandom.setOnClickListener(v -> {
            GenerateJoke fragment1 = new GenerateJoke();
            FragmentManager myManager1 = getSupportFragmentManager();
            FragmentTransaction myTransaction1 = myManager1.beginTransaction();
            myTransaction1.replace(R.id.mainScreen, fragment1);
            myTransaction1.commit();
        });

        Button bFavourites = findViewById(R.id.bCategories);
        bFavourites.setOnClickListener(v -> {
            FavouriteJokes fragment1 = new FavouriteJokes();
            FragmentManager myManager1 = getSupportFragmentManager();
            FragmentTransaction myTransaction1 = myManager1.beginTransaction();
            myTransaction1.replace(R.id.mainScreen, fragment1);
            myTransaction1.commit();
        });

    }
}
