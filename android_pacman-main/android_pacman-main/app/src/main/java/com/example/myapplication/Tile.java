package com.example.myapplication;

public class Tile {

    private final boolean isWall;
    private final boolean turnTile;

    private boolean pellet;
    private boolean enemy;
    private Pellet pelletType;

    public Tile (boolean isWall, boolean pellet) {
        this(isWall, pellet, false);
    }

    public Tile (boolean isWall, boolean pellet, boolean turnTile) {
        if (pellet) Global.setNumPellets(Integer.parseInt(Global.getNumPellets()) + 1);
        this.isWall = isWall;
        this.pellet = pellet;
        this.turnTile = turnTile;
        this.pelletType = Pellet.REGULAR;
        this.enemy = false;
    }

    public Tile (boolean isWall, boolean pellet, boolean turnTile, Pellet pelletType) {
        if (pellet) Global.setNumPellets(Integer.parseInt(Global.getNumPellets()) + 1);
        this.isWall = isWall;
        this.pellet = pellet;
        this.turnTile = turnTile;
        this.pelletType = pelletType;
        this.enemy = false;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean hasPellet() {
        return pellet;
    }

    public void setPellet(boolean pellet) {
        this.pellet = pellet;
    }

    public boolean isTurnTile() {
        return turnTile;
    }

    public boolean hasEnemy() { return enemy; }

    public void setEnemy(boolean enemy) { this.enemy = enemy; }

    public Pellet getPelletType() {
        return pelletType;
    }
}
