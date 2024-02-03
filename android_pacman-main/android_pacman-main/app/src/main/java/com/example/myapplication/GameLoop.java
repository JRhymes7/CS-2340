package com.example.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

// uhhh i hardly know how this even works i followed this tutorial tho
// https://www.youtube.com/watch?v=5iFOYfpKOfs
public class GameLoop extends Thread {

    private static final double MAX_UPS = 30;
    private static final double UPS_PERIOD = 1E+3 / MAX_UPS;
    private GameHandler gameHandler;
    private SurfaceHolder surfaceHolder;

    private boolean isRunning = false;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(GameHandler gameHandler, SurfaceHolder surfaceHolder) {
        super();
        this.gameHandler = gameHandler;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        long startTime;
        long elapsedTime;
        long sleepTime;
        int frameCount = 0;
        int updateCount = 0;
        super.run();

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning) {

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameHandler.update();
                    updateCount++;
                    this.gameHandler.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            elapsedTime =  System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                gameHandler.update();
                updateCount++;
                elapsedTime =  System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void setRunning (boolean isRunning) {
        this.isRunning = isRunning;
    }
}
