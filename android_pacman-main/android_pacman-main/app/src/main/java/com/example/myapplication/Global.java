package com.example.myapplication;

import android.app.Application;

public class Global extends Application {
    private static String playerName = "Player";
    private static int lives = 3;
    private static boolean livesFlag = false;
    private static int initialLives = 3;
    private static int livesLost = 0;
    private static String color = "yellow";
    private static int round = 1;
    private static int score = 0;
    private static int numPellets = 0;
    private static int ghostsEaten = 0;
    private static boolean powerPelletFlag = false;
    private static boolean speedPelletFlag = false;
    private static int powerTimer = 0;
    private static int speedTimer = 0;
    private static int blueTimer = 0;
    private static int orangeTimer = 0;
    private static int pinkTimer = 0;
    private static int redTimer = 0;

    public static String getPlayerName() { return playerName; }

    public static String getLives() {
        return String.valueOf(lives);
    }

    public static String getColor() {
        return color;
    }

    public static String getRound() { return String.valueOf(round);}

    public static String getScore() { return String.valueOf(score); }

    public static String getLivesLost() {
        if (lives <= 0) {
            return String.valueOf(initialLives);
        }
        return String.valueOf(initialLives - lives);
    }

    public static String getInitialLives() { return String.valueOf(initialLives); }

    public static String getNumPellets() { return String.valueOf(numPellets); }

    public static String getGhostsEaten() { return String.valueOf(ghostsEaten); }

    public static boolean getPowerPelletFlag() {
        return powerPelletFlag;
    }

    public static boolean getSpeedPelletFlag() {
        return speedPelletFlag;
    }

    public static int getPowerTimer() {
        return powerTimer;
    }

    public static int getSpeedTimer() {
        return speedTimer;
    }

    public static int getBlueTimer() {
        return blueTimer;
    }

    public static int getOrangeTimer() {
        return orangeTimer;
    }

    public static int getPinkTimer() {
        return pinkTimer;
    }

    public static int getRedTimer() {
        return redTimer;
    }

    public static void setPlayerName(String newPlayerName) { playerName = newPlayerName; }

    public static void setLives(int newLives) {
        lives = newLives;
    }

    public static void setColor(String newColor) {
        color = newColor;
    }

    public static void setRound(int newRound) { round = newRound; }

    public static void setScore(int newScore) { score = newScore; }

    public static void setInitialLives(int newIntialLives) {
        initialLives = newIntialLives;
    }

    public static void setNumPellets(int newNumPellets) { numPellets = newNumPellets; }

    public static void setGhostsEaten(int newGhostsEaten) { ghostsEaten = newGhostsEaten; }

    public static void setPowerPelletFlag(boolean powerPelletFlag) {
        Global.powerPelletFlag = powerPelletFlag;
    }

    public static void setSpeedPelletFlag(boolean speedPelletFlag) {
        Global.speedPelletFlag = speedPelletFlag;
    }

    public static void setPowerTimer(int powerTimer) {
        Global.powerTimer = powerTimer;
    }

    public static void setSpeedTimer(int speedTimer) {
        Global.speedTimer = speedTimer;
    }

    public static void setBlueTimer(int blueTimer) {
        Global.blueTimer = blueTimer;
    }

    public static void setOrangeTimer(int orangeTimer) {
        Global.orangeTimer = orangeTimer;
    }

    public static void setPinkTimer(int pinkTimer) {
        Global.pinkTimer = pinkTimer;
    }

    public static void setRedTimer(int redTimer) {
        Global.redTimer = redTimer;
    }
}
