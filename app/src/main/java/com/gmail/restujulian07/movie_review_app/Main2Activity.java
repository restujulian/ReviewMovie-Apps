package com.gmail.restujulian07.movie_review_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void pesan(View view) {
        Intent movie= new Intent(Main2Activity.this, MainActivity.class);
        startActivity(movie);
    }
}
