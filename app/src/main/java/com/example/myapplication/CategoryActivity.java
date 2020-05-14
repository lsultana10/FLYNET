package com.example.myapplication;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;



public class CategoryActivity extends NavigationActivity {

    private LinearLayout history,travel,food,animals,technology,music,filmography,art,sports;
    public static final String TAG = "TAG";
    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The code that directs the menu to this page
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //This is the FrameLayout area within activity_navigation.xml
        getLayoutInflater().inflate(R.layout.activity_navigation, contentFrameLayout);

        super.onCreate(savedInstanceState);

        //Code to make menu visible on each activity
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_category,null,false);
        drawerLayout.addView(contentView,0);
        //end

        history=findViewById(R.id.history);
        travel=findViewById(R.id.travel);
        food=findViewById(R.id.food);
        animals=findViewById(R.id.animals);
        technology=findViewById(R.id.technology);
        music=findViewById(R.id.music);
        filmography=findViewById(R.id.filmography);
        art=findViewById(R.id.art);
        sports=findViewById(R.id.sports);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected History.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Travel.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, TravelActivity.class);
                startActivity(intent);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Food.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Animals.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, AnimalActivity.class);
                startActivity(intent);
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Technology.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, TechActivity.class);
                startActivity(intent);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Music.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
        filmography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Filmography.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, FilmActivity.class);
                startActivity(intent);
            }
        });
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Art.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, ArtActivity.class);
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this,"You have selected Sports.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CategoryActivity.this, SportActivity.class);
                startActivity(intent);
            }
        });
    }

}