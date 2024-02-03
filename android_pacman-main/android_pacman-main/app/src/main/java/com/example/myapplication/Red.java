package com.example.myapplication;

public class Red extends Enemy {

    public Red(PacMan pacman, Map map) {
        super(9, 7, Direction.RIGHT, 0.12, pacman.getX(), pacman.getY(), pacman, map);
    }

    /**
     * Target Tile is set to Pacman's current location.
     *
     * @param pacman the pacman character used for tracking
     * @param map the Tile map
     */
    @Override
    public void updateTargetTile(PacMan pacman, Map map) {
        if (Global.getPowerPelletFlag()) {
            scatter();
            return;
        }
        setTargetTile(getPacman().getX(), getPacman().getY());
    }

    public void scatter() {
        setTargetTile(0, 0);
    }
}
