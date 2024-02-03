package com.example.myapplication;

public class Pink extends Enemy {

    public Pink(int targetX, int targetY, PacMan pacman, Map map) {
        super(9, 7, Direction.RIGHT, 0.1, targetX, targetY, pacman, map);
    }

    /**
     * Sets the target Tile to the Tile 4 units in front of Pacman.
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
        switch(getPacman().getCurrentDir()) {
            case UP:
                setTargetTile(pacman.getX(), (pacman.getY() - 4) % map.getMap().length);
                break;
            case DOWN:
                setTargetTile(pacman.getX(), (pacman.getY() + 4) % map.getMap().length);
                break;
            case LEFT:
                setTargetTile((pacman.getX() - 4) % map.getMap()[0].length, pacman.getY());
                break;
            default:
                setTargetTile((pacman.getX() + 4) % map.getMap()[0].length, pacman.getY());
                break;
        }
    }

    protected void scatter() {
        setTargetTile(19, 0);
    }
}
