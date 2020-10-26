package com.seadog;

import javax.swing.ImageIcon;

// 벽돌 클래스
public class Brick extends Sprite {
    private boolean destroyed;

    public Brick(int x, int y) {
        initBrick(x, y);
    }

    public void initBrick(int x, int y) {
        this.x = x;
        this.y = y;

        destroyed = false;

        loadImage();
        getImageDimensions();
    }

    public void loadImage() {
        var ii = new ImageIcon("resources/brick.png");
        image = ii.getImage();
    }

    boolean isDestroyed() {
        return destroyed;
    }

    void setDestroyed(boolean val) {
        destroyed = val;
    }
}
