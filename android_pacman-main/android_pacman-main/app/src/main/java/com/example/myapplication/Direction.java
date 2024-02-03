package com.example.myapplication;

enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction reverse(Direction dir) {
        switch (dir) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case RIGHT:
                return LEFT;
            default:
                return RIGHT;
        }
    }
}