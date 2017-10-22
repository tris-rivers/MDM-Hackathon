package com.example.searchandrescue_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SlideR1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_r1);
    }

    public void goToSlideR2(View view){
        Intent intent = new Intent(this, SlideR2.class);
        startActivity(intent);
    }
}
