package com.example.myapplication;

public class Blue extends Enemy {

    public Blue(PacMan pacman, Map map) {
        super(9, 7, Direction.RIGHT, 0.1,
                (pacman.getX() * 2) % map.getMap()[0].length,
                (pacman.getY() * 2) % map.getMap().length, pacman, map);
    }

    /**
     * Calculates the distance to Pacman, multiplies by 2, and sets that Tile as the target. Uses
     * modulo when the target Tile is out of bounds.
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
        int tempTargetX = (pacman.getX() * 2) % map.getMap()[0].length;
        int tempTargetY = (pacman.getY() * 2) % map.getMap().length;
        setTargetTile(tempTargetX, tempTargetY);
    }

    protected void scatter() {
        setTargetTile(19, 21);
    }
}
