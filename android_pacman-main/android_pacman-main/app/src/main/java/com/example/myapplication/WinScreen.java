package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_screen);
        // put player's name on screen
        TextView playerName = (TextView) findViewById(R.id.playerNameLabel2);
        playerName.setText(Global.getPlayerName());
        // set score to global score variable
        TextView score = (TextView) findViewById(R.id.score);
        score.setText(Global.getScore());
        // set lives lost to global lives lost variable
        TextView livesLostText = (TextView) findViewById(R.id.livesLost);
        livesLostText.setText(Global.getLivesLost());
        // set ghosts eaten to global ghosts eaten variable
        TextView ghostsEatenText = (TextView) findViewById(R.id.eaten2);
        ghostsEatenText.setText(Global.getGhostsEaten());

        // if restart button is clicked, open configuration screen (MainActivity2)
        Button start = findViewById(R.id.restartButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfig();
            }
        });

        // if exit button is clicked, exit game
        Button extButton = findViewById(R.id.exitButton3);
        extButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    public void openConfig() {
        Global.setScore(0);
        Global.setLives(3);
        Intent intent = new Intent(this, ConfigScreen.class);
        startActivity(intent);
    }
}