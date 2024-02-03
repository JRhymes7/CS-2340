package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConfigScreen extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);

        Button contBtn = findViewById(R.id.contButton);
        EditText name = findViewById(R.id.editTextName);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);

        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if name is null, tell player they can't pass in null
                if (name.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Player name cannot be null",
                            Toast.LENGTH_SHORT).show();
                }
                // if name is empty or whitespace, tell player to enter real characters
                // if a difficulty is not selected, tell player to select it
                // if a color is not selected, tell player to select it
                // if config screen is filled out, go to game screen (MainActivity3)
                String nameString = name.getText().toString().trim();
                Global.setPlayerName(nameString);
                if (nameString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Player name cannot be empty, " +
                            "null, or whitespace only characters", Toast.LENGTH_SHORT).show();
                } else if (radioGroup1.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select a difficulty level",
                            Toast.LENGTH_SHORT).show();
                } else if (radioGroup2.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select a pacman color",
                            Toast.LENGTH_SHORT).show();
                } else {
                    openActivity3();
                }
            }
        });
    }
    // open game screen
    public void openActivity3() {
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }

    // check that a difficulty button is selected
    public void checkButtonDifficulty(View v) {
        int radioId = radioGroup1.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        // set global variable lives
        if (radioButton.getText().equals("easy")) {
            Global.setLives(5);
            Global.setInitialLives(5);
        } else if (radioButton.getText().equals("medium")) {
            Global.setLives(3);
            Global.setInitialLives(3);
        } else if (radioButton.getText().equals("hard")) {
            Global.setLives(1);
            Global.setInitialLives(1);
        }
        System.out.println(Global.getLives());
        Toast.makeText(this, "Selected " + radioButton.getText() + " difficulty level", Toast.LENGTH_SHORT).show();
    }

    // check that a player color is selected
    public void checkButtonPlayer(View v) {
        int radioId = radioGroup2.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        // set global variable color
        if (radioButton.getText().equals("yellow")) {
            Global.setColor("yellow");
        } else if (radioButton.getText().equals("red")) {
            Global.setColor("red");
        } else if (radioButton.getText().equals("blue")) {
            Global.setColor("blue");
        }
        System.out.println(Global.getColor());
        Toast.makeText(this, "Selected " + radioButton.getText() + " pacman", Toast.LENGTH_SHORT).show();
    }

}