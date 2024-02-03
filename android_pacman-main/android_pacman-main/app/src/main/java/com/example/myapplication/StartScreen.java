package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {

    // private DrawView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if exit button is clicked, exit game
        Button extButton = findViewById(R.id.exitButton);
        extButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        // if start button is clicked, open configuration screen (MainActivity2)
        Button start = findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });


    }
    // TODO: Refactor activity names
    // open MainActivity2 aka configuration screen
    public void openActivity2() {
        Intent intent = new Intent(this, ConfigScreen.class);
        startActivity(intent);
    }
}