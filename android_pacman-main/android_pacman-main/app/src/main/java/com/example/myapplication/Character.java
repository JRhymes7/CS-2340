package com.example.myapplication;

import android.graphics.Bitmap;

/**
 * A Character is an object that stores a sprite that can be displayed, and then move based on a
 * Tile map.
 */
public abstract class Character {

        protected int x, y;
        private Direction currentDir;
        private Direction nextDir;
        private double speed;
        private double xOffset;
        private double yOffset;
        private Bitmap sprite;

        public Character (int x, int y, Direction currentDir, double speed) {
                this.x = x;
                this.y = y;
                this.currentDir = currentDir;
                this.speed = speed;
                this.xOffset = 0;
                this.yOffset = 0;
        }

        /**
         * Updates the visual offset of the Character based on its speed, and updates the x and y
         * once the offset reaches a breakpoint.
         *
         * @param map a map that stores the types of tiles that the character is traversing
         */
        public void move(Map map) {
                // Adjusts the Character's location parameters to its current direction accordingly
                switch (currentDir) {
                        case UP:
                                if (!map.getMap()[y - 1][x].isWall()) {
                                        yOffset -= this.speed;
                                }
                                break;
                        case DOWN:
                                if (!map.getMap()[y + 1][x].isWall()) {
                                        yOffset += this.speed;
                                }
                                break;
                        case LEFT:
                                if (!map.getMap()[y][x - 1].isWall()) {
                                        xOffset -= this.speed;
                                }
                                break;
                        case RIGHT:
                                if (!map.getMap()[y][x + 1].isWall()) {
                                        xOffset += this.speed;
                                }
                                break;
                }

                if (xOffset >= 1 || xOffset <= -1) {
                        x = xOffset >= 1 ? x + 1 : x - 1;
                        xOffset = 0;
                        if (this instanceof Enemy) {
                                ((Enemy) this).setTurnTileFlag(false);
                        }
                        if (nextDir == Direction.UP || nextDir == Direction.DOWN) {
                                if (!map.getMap()[nextDir == Direction.UP ? y - 1 : y + 1][x].isWall()) {
                                        currentDir = nextDir;
                                        nextDir = null;
                                }
                        }
                } else if (yOffset >= 1 || yOffset <= -1) {
                        y = yOffset >= 1 ? y + 1 : y - 1;
                        yOffset = 0;
                        if (this instanceof Enemy) {
                                ((Enemy) this).setTurnTileFlag(false);
                        }
                        if (nextDir == Direction.RIGHT || nextDir == Direction.LEFT) {
                                if (!map.getMap()[y][nextDir == Direction.RIGHT ? x + 1 : x - 1].isWall()) {
                                        currentDir = nextDir;
                                        nextDir = null;
                                }
                        }
                }
        }

        /**
         * Uses the Character's current x and y positions to determine if the tile in the given
         * direction is a wall.
         *
         * @param map the map to determine the location of the walls
         * @param dir the direction being checked
         * @return true if there is a wall in the specified direction
         */
        public boolean wallAt(Map map, Direction dir) {
                switch (dir) {
                        case UP:
                                return map.getMap()[y - 1][x].isWall();
                        case DOWN:
                                return map.getMap()[y + 1][x].isWall();
                        case RIGHT:
                                return map.getMap()[y][x + 1].isWall();
                        default:
                                return map.getMap()[y][x - 1].isWall();
                }
        }

        public void reset(int startX, int startY, Direction direction, Map map) {
                map.getMap()[y][x].setEnemy(false);
                currentDir = direction;
                nextDir = null;
                this.x = startX;
                this.y = startY;
                xOffset = 0;
                yOffset = 0;
        }
        //Getters and Setters for the Character objects
        public Bitmap getSprite() {
                return sprite;
        }

        public void setSprite(Bitmap sprite) {
                this.sprite = sprite;
        }

        public int getX() {
                return x;
        }

        public int getY() {
                return y;
        }

        public void setX(int x) {
                this.x = x;
        }

        public void setY(int y) {
                this.y = y;
        }

        public Direction getCurrentDir() {
                return currentDir;
        }

        public void setCurrentDir(Direction currentDir) {
                this.currentDir = currentDir;
        }

        public Direction getNextDir() {
                return nextDir;
        }

        public void setNextDir(Direction nextDir) {
                this.nextDir = nextDir;
        }

        public double getXOffset() {
                return xOffset;
        }

        public void setXOffset(double xOffset) {
                this.xOffset = xOffset;
        }

        public double getYOffset() {
                return yOffset;
        }

        public void setYOffset(double yOffset) {
                this.yOffset = yOffset;
        }

        public void setSpeed(double speed) {
                this.speed = speed;
        }
}
