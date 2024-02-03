package com.example.myapplication;

public class PacMan extends Character {

    private int pelletsEaten;
    private int powerUpsEaten;
    // number of pellets the pacman has the potential of eating
    // how many total pellets are there in the maze?
    // private final int MAX_PELLETS = 146;
    // private int score;
    //private int lives;

    public PacMan() {
        super(9, 15, Direction.UP, 0.2);
        pelletsEaten = 0;
        powerUpsEaten = 0;
        // score = 0;
    }

    /**
     * Checks the Tile map at Pacman's coordinates, and eats a pellet if possible.
     *
     * @param map the Tile map
     */
    public void updateScore(Map map) {
        Tile currTile = map.getMap()[y][x];
        if (currTile.hasPellet()) {
            int currScore = Integer.parseInt(Global.getScore());
            currTile.setPellet(false);
            Global.setScore(currScore + 10);
            pelletsEaten++;
            switch (currTile.getPelletType()) {
                case POWER:
                    Global.setScore(currScore + 40);
                    powerUpsEaten++;
                    Global.setPowerPelletFlag(true);
                    Global.setPowerTimer(0);
                    break;
                case SPEED:
                    Global.setSpeedPelletFlag(true);
                    Global.setSpeedTimer(0);
                    this.setSpeed(0.25);
                    break;
                case DOUBLE:
                    Global.setScore(currScore * 2);
                    break;
            }
        }
    }

    /**
     * Checks to see if an Enemy is on the same Tile as Pacman. Causes Pacman to lose a life, and
     * ends the game if lives reach 0.
     *
     * @param map the Tile map being checked
     * @param handler the handler that handles game logic
     * @param gameLoop the game clock
     */
    public void checkEnemy(Map map, GameHandler handler, GameLoop gameLoop) {
        Tile currTile = map.getMap()[y][x];
        if (currTile.hasEnemy()) {
            if (Global.getPowerPelletFlag()) {
                int score = Integer.parseInt(Global.getScore());
                if (handler.getRed().getX() == x && handler.getRed().getY() == y) {
                    //Unfinished ghost death interaction
                    Global.setScore(score + 400);
                    Global.setRedTimer(100);
                    handler.getRed().reset(9, 7, Direction.RIGHT, map);
                    Global.setGhostsEaten(Integer.parseInt(Global.getGhostsEaten()) + 1);
                }
                if (handler.getPink().getX() == x && handler.getPink().getY() == y) {
                    //Unfinished ghost death interaction
                    Global.setScore(score + 400);
                    Global.setPinkTimer(100);
                    handler.getPink().reset(9, 7, Direction.RIGHT, map);
                    Global.setGhostsEaten(Integer.parseInt(Global.getGhostsEaten()) + 1);
                }
                if (handler.getBlue().getX() == x && handler.getBlue().getY() == y) {
                    //EAT GHOST INTERACTION
                    Global.setScore(score + 400);
                    Global.setBlueTimer(100);
                    handler.getBlue().reset(9, 7, Direction.RIGHT, map);
                    Global.setGhostsEaten(Integer.parseInt(Global.getGhostsEaten()) + 1);
                }
                if (handler.getOrange().getX() == x && handler.getOrange().getY() == y) {
                    //EAT GHOST INTERACTION
                    Global.setScore(score + 400);
                    Global.setOrangeTimer(100);
                    handler.getOrange().reset(9, 7, Direction.RIGHT, map);
                    Global.setGhostsEaten(Integer.parseInt(Global.getGhostsEaten()) + 1);
                }
                return;
            }
            Global.setLives(Integer.parseInt(Global.getLives()) - 1);
            // Global.setLivesLost(Integer.parseInt(Global.getLivesLost()) + 1);
            // System.out.println("1 life lost: " + Global.getLivesLost());
            if (Integer.parseInt(Global.getLives()) <= 0) {
                gameLoop.setRunning(false);
                handler.gameOver();
            } else {
                handler.resetCharacters();
            }
        }
    }

    public void checkWin(GameHandler handler) {
        if (pelletsEaten == Integer.parseInt(Global.getNumPellets())) {
            handler.winGame();
        }
    }

    //Getter methods
    public int getPelletsEaten() {
        return pelletsEaten;
    }

    public int getPowerUpsEaten() {
        return powerUpsEaten;
    }

    // public int getScore() { return score; }
}


