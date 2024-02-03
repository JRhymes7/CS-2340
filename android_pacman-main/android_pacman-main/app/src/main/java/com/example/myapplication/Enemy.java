package com.example.myapplication;

public abstract class Enemy extends Character {

    private int targetX;
    private int targetY;
    private boolean turnTileFlag;

    private final PacMan pacman;
    private final Map currMap;

    public Enemy(int x, int y, Direction currentDir, double speed, int targetX, int targetY, PacMan pacman, Map map) {
        super(x, y, currentDir, speed);
        this.targetX = targetX;
        this.targetY = targetY;

        this.pacman = pacman;
        this.currMap = map;

        this.turnTileFlag = false;
    }

    /**
     * Updates the backing information of the given Enemy and then moves it.
     */
    public void updateEnemy() {
        Tile currTile = currMap.getMap()[y][x];
        if (currTile.isTurnTile() && !turnTileFlag) {
            updateTargetTile(pacman, currMap);
            turnTileUpdateHelper(targetX - x, targetY - y);
            turnTileFlag = true;
        }
        handleWall();
        currTile.setEnemy(false);
        this.move(currMap);
        currMap.getMap()[y][x].setEnemy(true);
    }

    /**
     * Helper method that takes the vector components for the vector to the target tile
     * and greedily sets directions to reach the target tile without reversing directions.
     *
     * @param vX Vector component on the x-axis to target tile
     * @param vY Vector component on the y-axis to target tile
     */
    private void turnTileUpdateHelper(int vX, int vY) {
        Direction prevDir = getCurrentDir();
        if (Math.abs(vX) >= Math.abs(vY)) {
            setCurrentDir(vX < 0 ? Direction.LEFT : Direction.RIGHT);
            setNextDir(vY < 0 ? Direction.UP : Direction.DOWN);
        } else {
            setCurrentDir(vY < 0 ? Direction.UP : Direction.DOWN);
            setNextDir(vX < 0 ? Direction.LEFT : Direction.RIGHT);
        }

        // Section to prevent the ghost from reversing direction in vast majority of cases
        // May still cause reversing in cases when PacMan can't die, clean up in future
        if (prevDir == Direction.reverse(getCurrentDir())) {
            setCurrentDir(getNextDir());
            setNextDir(null);
        } else if (prevDir == Direction.reverse(getNextDir())) {
            setNextDir(prevDir);
        }

        // Special case when one of the vector components is a 0, prevents inefficient turns
        if (vX == 0 || vY == 0) {
            setNextDir(null);
        }
    }

    /**
     * Helper method to prevent wall collisions. Prioritize turning left and up over right and down.
     */
    private void handleWall() {
        for (int i = 0; i < 2; i++) {
            // Arbitrarily determines where to go next when encountering a wall
            // Loop twice in cases where the arbitrary direction is also a wall
            if (getNextDir() == null) {
                if (getCurrentDir() == Direction.UP || getCurrentDir() == Direction.DOWN) {
                    setNextDir(currMap.getMap()[y][x - 1].isWall() ? Direction.RIGHT : Direction.LEFT);
                } else {
                    setNextDir(currMap.getMap()[y - 1][x].isWall() ? Direction.DOWN : Direction.UP);
                }
            }
            //Handle next direction being a wall
            if (wallAt(currMap, getCurrentDir())) {
                setCurrentDir(getNextDir());
                setNextDir(null);
            }
        }
        //Clear next direction after updating current direction
        setNextDir(null);
    }


    public void setTargetTile(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public PacMan getPacman() {
        return pacman;
    }

    public Map getCurrMap() {
        return currMap;
    }

    public void setTurnTileFlag(boolean turnTileFlag) {
        this.turnTileFlag = turnTileFlag;
    }

    protected abstract void updateTargetTile(PacMan pacman, Map map);

    protected abstract void scatter();
}
