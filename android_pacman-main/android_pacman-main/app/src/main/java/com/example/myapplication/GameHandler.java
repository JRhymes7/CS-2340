package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

// Performs all of the actual game operations
public class GameHandler extends SurfaceView implements SurfaceHolder.Callback {

    private Context context;
    private final GameLoop gameLoop;
    private PacMan pacman;
    private Red red;
    private Blue blue;
    private Pink pink;
    private Orange orange;
    private Map map;

    public GameHandler(Context context, Map map, PacMan pacman) {
        super(context);

        getHolder().addCallback(this);

        this.context = context;
        gameLoop = new GameLoop(this, getHolder());
        this.map = map;
        this.pacman = pacman;
        this.red = new Red(pacman, map);
        this.blue = new Blue(pacman, map);
        //Pacman starts facing upwards
        this.pink = new Pink(pacman.getX(), pacman.getY() - 4, pacman, map);
        this.orange = new Orange(pacman, map);
        setFocusable(true);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        //Set the ghost sprites
        red.setSprite(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.redghost),
                canvas.getWidth() / map.getMap()[0].length, canvas.getHeight() / map.getMap().length, false));
        blue.setSprite(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.lightblueghost),
                canvas.getWidth() / map.getMap()[0].length, canvas.getHeight() / map.getMap().length, false));
        pink.setSprite(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pinkghost),
                canvas.getWidth() / map.getMap()[0].length, canvas.getHeight() / map.getMap().length, false));
        orange.setSprite(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.orangeghost),
                canvas.getWidth() / map.getMap()[0].length, canvas.getHeight() / map.getMap().length, false));
        //Uses basePacsprite for direction implementation
        if (pacman.getSprite() == null) {
            if (Global.getColor() == "yellow") {
                pacman.setSprite(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pacman_shape_yellow),
                        canvas.getWidth() / map.getMap()[0].length, canvas.getHeight() / map.getMap().length, false));
            } else if (Global.getColor() == "red") {
                pacman.setSprite(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pacman_shape_red),
                        canvas.getWidth() / map.getMap()[0].length, canvas.getHeight() / map.getMap().length, false));
            } else if (Global.getColor() == "blue") {
                pacman.setSprite(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pacman_shape_blue),
                        canvas.getWidth() / map.getMap()[0].length, canvas.getHeight() / map.getMap().length, false));
            }
        }
        super.onDraw(canvas);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.setRunning(true);
        gameLoop.start();
        //gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                gameLoop.setRunning(false);
                gameLoop.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawMap(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        drawScore(canvas);
        drawLives(canvas);
        drawRounds(canvas);
        drawPacMan(canvas, pacman);
        int pacScore = Integer.parseInt(Global.getScore());
        if (Global.getRedTimer() == 0) {
            drawEnemy(canvas, red);
        }
        if (pacScore > 150 && Global.getPinkTimer() == 0) {
            drawEnemy(canvas, pink);
        }
        if (pacScore > 300 && Global.getBlueTimer() == 0) {
            drawEnemy(canvas, blue);
        }
        if (pacScore > 450 && Global.getOrangeTimer() == 0) {
            drawEnemy(canvas, orange);
        }
    }

    public void drawUPS(@NonNull Canvas canvas) {
        String averageUPS = Integer.toString((int)gameLoop.getAverageUPS());
        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.parseColor("#FBFBFB"));
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 50, paint);
    }

    public void drawFPS(@NonNull Canvas canvas) {
        String averageFPS = Integer.toString((int)gameLoop.getAverageFPS());
        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.parseColor("#FBFBFB"));
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 400, 50, paint);
    }

    public void drawScore(@NonNull Canvas canvas) {
        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.parseColor("#FBFBFB"));
        paint.setTextSize(50);
        canvas.drawText("Score: " + Global.getScore(), 700, 50, paint);
    }

    public void drawLives(@NonNull Canvas canvas) {
        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.parseColor("#FBFBFB"));
        paint.setTextSize(50);
        canvas.drawText("Lives: " + Global.getLives(), 100, 1190, paint);
    }

    public void drawRounds(@NonNull Canvas canvas) {
        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.parseColor("#FBFBFB"));
        paint.setTextSize(50);
        canvas.drawText("Round: " + Global.getRound(), 700, 1190, paint);
    }

    public void drawMap(@NonNull Canvas canvas) {

        float left = 0;
        float top = 0;
        float right = canvas.getWidth() / map.getMap()[0].length;
        float bottom = canvas.getHeight() / map.getMap().length;

        Paint blue = new Paint();
        blue.setColor(Color.parseColor("#2121DE"));
        blue.setStyle(Paint.Style.FILL);

        Paint green = new Paint();
        green.setColor(Color.parseColor("#0FFF0F"));
        green.setStyle(Paint.Style.FILL);

        Paint white = new Paint();
        white.setColor(Color.parseColor("#FBFBFB"));
        white.setStyle(Paint.Style.FILL);

        Paint red = new Paint();
        red.setColor(Color.parseColor("#FF3333"));
        red.setStyle(Paint.Style.FILL);

        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap()[0].length; j++) {

                // Uncomment to display when a tile is tracking an Enemy
                /*
                if (map.getMap()[i][j].hasEnemy()) {
                    canvas.drawRect(left, top, right, bottom, red);
                }
                */

                //draws walls
                if (map.getMap()[i][j].isWall()) {
                    canvas.drawRect(left, top, right, bottom, blue);
                //draws pellet tiles
                } else if (map.getMap()[i][j].hasPellet()
                        && map.getMap()[i][j].getPelletType() == Pellet.REGULAR) {
                    canvas.drawCircle(left + (canvas.getWidth() / map.getMap()[0].length) / 2,
                            top + (canvas.getHeight() / map.getMap().length) / 2, 8, white);
                //draws power pellet tiles
                } else if (map.getMap()[i][j].hasPellet()
                        && map.getMap()[i][j].getPelletType() == Pellet.POWER) {
                    canvas.drawCircle(left + (canvas.getWidth() / map.getMap()[0].length) / 2,
                            top + (canvas.getHeight() / map.getMap().length) / 2, 20, white);
                } else if (map.getMap()[i][j].hasPellet()
                        && map.getMap()[i][j].getPelletType() == Pellet.SPEED) {
                    canvas.drawCircle(left + (canvas.getWidth() / map.getMap()[0].length) / 2,
                            top + (canvas.getHeight() / map.getMap().length) / 2, 8, green);
                } else if (map.getMap()[i][j].hasPellet()
                        && map.getMap()[i][j].getPelletType() == Pellet.DOUBLE) {
                    canvas.drawCircle(left + (canvas.getWidth() / map.getMap()[0].length) / 2,
                            top + (canvas.getHeight() / map.getMap().length) / 2, 8, red);
                }
                left = right;
                right += canvas.getWidth() / map.getMap()[0].length;
            }
            left = 0;
            right = canvas.getWidth() / map.getMap()[0].length;
            top = bottom;
            bottom += canvas.getHeight() / map.getMap().length;
        }
    }

    public void drawPacMan(Canvas canvas, @NonNull PacMan pacman) {
        Bitmap pacmanSprite;
        Direction currDirection = pacman.getCurrentDir();
        Bitmap rotatedPacmanSprite;
        Matrix matrix = new Matrix();
        switch(currDirection) {
            case UP:
                matrix.postRotate(-90);
                rotatedPacmanSprite = Bitmap.createBitmap(pacman.getSprite(), 0, 0, +
                        + pacman.getSprite().getWidth(), pacman.getSprite().getHeight(), matrix, true);
                pacmanSprite = rotatedPacmanSprite;
                break;
            case LEFT:
                matrix.postRotate(180);
                rotatedPacmanSprite = Bitmap.createBitmap(pacman.getSprite(), 0, 0, +
                        + pacman.getSprite().getWidth(), pacman.getSprite().getHeight(), matrix, true);
                pacmanSprite = rotatedPacmanSprite;
                break;
            case DOWN:
                matrix.postRotate(90);
                rotatedPacmanSprite = Bitmap.createBitmap(pacman.getSprite(), 0, 0, +
                        + pacman.getSprite().getWidth(), pacman.getSprite().getHeight(), matrix, true);
                pacmanSprite = rotatedPacmanSprite;
                break;
            default:
                //Sets to face right
                pacmanSprite = pacman.getSprite();
        }
        canvas.drawBitmap(pacmanSprite, (float)((pacman.getX() + pacman.getXOffset()) * (canvas.getWidth() / map.getMap()[0].length)),
                (float)((pacman.getY() + pacman.getYOffset()) * (canvas.getHeight() / map.getMap().length)), null);
    }

    public void drawEnemy(@NonNull Canvas canvas, @NonNull Enemy enemy) {
        canvas.drawBitmap(enemy.getSprite(), (float)((enemy.getX() + enemy.getXOffset()) * (canvas.getWidth() / map.getMap()[0].length)),
                (float)((enemy.getY() + enemy.getYOffset()) * (canvas.getHeight() / map.getMap().length)), null);
    }

    public void update() {
        //Speed buff lasts for 150 ticks, so around 5 seconds
        if (Global.getSpeedTimer() > 150) {
            Global.setSpeedPelletFlag(false);
            Global.setSpeedTimer(0);
            pacman.setSpeed(0.2);
        }
        //Power buff lasts for 150 ticks, so around 5 seconds
        if (Global.getPowerTimer() > 150) {
            Global.setPowerPelletFlag(false);
            Global.setPowerTimer(0);
        }
        pacman.move(map);

        int pacScore = Integer.parseInt(Global.getScore());
        if (Global.getRedTimer() == 0) {
            red.updateEnemy();
        }
        if (pacScore > 150 && Global.getPinkTimer() == 0) {
            pink.updateEnemy();
        }
        if (pacScore > 300 && Global.getBlueTimer() == 0) {
            blue.updateEnemy();
        }
        if (pacScore > 450 && Global.getOrangeTimer() == 0) {
            orange.updateEnemy();
        }
        pacman.checkEnemy(map, this, gameLoop);
        pacman.updateScore(map);
        pacman.checkWin(this);

        //Update timers each tick if necessary
        if (Global.getPowerPelletFlag()) {
            Global.setPowerTimer(Global.getPowerTimer() + 1);
        }
        if (Global.getSpeedPelletFlag()) {
            Global.setSpeedTimer(Global.getSpeedTimer() + 1);
        }
        if (Global.getBlueTimer() > 0) {
            Global.setBlueTimer(Global.getBlueTimer() - 1);
        }
        if (Global.getOrangeTimer() > 0) {
            Global.setOrangeTimer(Global.getOrangeTimer() - 1);
        }
        if (Global.getPinkTimer() > 0) {
            Global.setPinkTimer(Global.getPinkTimer() - 1);
        }
        if (Global.getRedTimer() > 0) {
            Global.setRedTimer(Global.getRedTimer() - 1);
        }
    }

    public void resetCharacters() {
        pacman.reset(9, 15, Direction.UP, map);
        red.reset(9, 7, Direction.RIGHT, map);
        pink.reset(9, 7, Direction.RIGHT, map);
        blue.reset(9, 7, Direction.RIGHT, map);
        orange.reset(9, 7, Direction.RIGHT, map);
    }

    public void gameOver() {
        Intent intent = new Intent(context, GameOverScreen.class);
        context.startActivity(intent);
    }

    public void winGame() {
        Intent intent = new Intent(context, WinScreen.class);
        gameLoop.setRunning(false);
        context.startActivity(intent);
    }

    public Enemy getRed() {
        return red;
    }

    public Enemy getPink() {
        return pink;
    }

    public Enemy getBlue() {
        return blue;
    }

    public Enemy getOrange() {
        return orange;
    }
}

