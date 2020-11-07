package com.seadog;

import javax.swing.ImageIcon;

public class Ball extends Sprite {
    private int xdir;
    private int ydir;

    public Ball() {
        initBall();
    }

    private void initBall() {
        xdir = 2;
        ydir = -2;

        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {
        var ii = new ImageIcon("resources/ball.png");
        image = ii.getImage();
    }

    void move() {
        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(2);
        }

        if (x >= Commons.WIDTH - imageWidth) {
            // 이거 왜 출력??
            // System.out.println(imageWidth);
            setXDir(-2);
        }

        if (y == 0) {
            setYDir(2);
        }
    }

    private void resetState() {
        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    void setXDir(int x) {
        xdir = x;
    }

    void setYDir(int y) {
        ydir = y;
    }

    int getYDir() {
        return ydir;
    }
}
