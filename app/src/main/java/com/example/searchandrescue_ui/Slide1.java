package com.example.searchandrescue_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Slide1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide1);
    }

    public void goToSlide2(View view){
        Intent intent = new Intent(this, Slide2.class);
        startActivity(intent);
    }
}
