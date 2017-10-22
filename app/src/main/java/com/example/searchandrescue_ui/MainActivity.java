package com.example.searchandrescue_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendRescue(View view){
        Intent intent = new Intent(this, RescueMe.class);
        startActivity(intent);
    }

    public void sendWillRescue(View view){
        Intent intent1 = new Intent(this, WillRescue.class);
        startActivity(intent1);
    }
}
