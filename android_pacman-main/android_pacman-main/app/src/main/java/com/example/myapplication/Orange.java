package com.example.myapplication;

public class Orange extends Enemy {

    public Orange(PacMan pacman, Map map) {
        super(9, 7, Direction.RIGHT, 0.1, pacman.getX(), pacman.getY(), pacman, map);
    }

    /**
     * Target Tile is at Pacman's location, but once Orange is within a distance of 8
     * units, the target Tile is set to the bottom left corner of the Tile map.
     *
     * @param pacman the pacman character used for tracking
     * @param map the Tile map
     */
    @Override
    protected void updateTargetTile(PacMan pacman, Map map) {
        if (Global.getPowerPelletFlag()) {
            scatter();
            return;
        }
        double distance = Math.sqrt(Math.pow((x - pacman.getX()), 2) + Math.pow((y - pacman.getY()), 2));
        if (distance <=8 ) {
            setTargetTile(0, map.getMap().length);
        } else {
            setTargetTile(pacman.getX(), pacman.getY());
        }
    }

    protected void scatter() {
        setTargetTile(0, 21);
    }
}
