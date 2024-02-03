package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    TextView numLives;
    TextView numRounds;
    Map map;
    PacMan pacman;
    ConstraintLayout screenLayout;

    private Button up;
    private Button down;
    private Button left;
    private Button right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_activity);

        up = findViewById(R.id.upButton);
        down = findViewById(R.id.downButton);
        left = findViewById(R.id.leftButton);
        right = findViewById(R.id.rightButton);

        map = new Map();
        pacman = new PacMan();
        screenLayout = (ConstraintLayout) findViewById(R.id.screenLayout);
        screenLayout.addView(new GameHandler(this, map, pacman));

        up.setOnClickListener(v -> {
            if (pacman.getXOffset() == 0 && !pacman.wallAt(map, Direction.UP)) {
                pacman.setCurrentDir(Direction.UP);
            } else {
                pacman.setNextDir(Direction.UP);
            }
        });

        down.setOnClickListener(v -> {
            if (pacman.getXOffset() == 0 && !pacman.wallAt(map, Direction.DOWN)) {
                pacman.setCurrentDir(Direction.DOWN);
            } else {
                pacman.setNextDir(Direction.DOWN);
            }
        });

        left.setOnClickListener(v -> {
            if (pacman.getYOffset() == 0 && !pacman.wallAt(map, Direction.LEFT)) {
                pacman.setCurrentDir(Direction.LEFT);
            } else {
                pacman.setNextDir(Direction.LEFT);
            }
        });

        right.setOnClickListener(v -> {
            if (pacman.getYOffset() == 0 && !pacman.wallAt(map, Direction.RIGHT)) {
                pacman.setCurrentDir(Direction.RIGHT);
            } else {
                pacman.setNextDir(Direction.RIGHT);
            }
        });


    }
}