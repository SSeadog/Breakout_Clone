package com.seadog;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite {
    boolean leftIsDown;
    boolean rightIsDown;

    private int dx;

    public Paddle() {
        initPaddle();
    }

    private void initPaddle() {
        loadImage();
        getImageDimensions();

        resetState();
    }

    private void loadImage() {
        var ii = new ImageIcon("resources/paddle.png");
        image = ii.getImage();
    }

    void move() {
        if (leftIsDown) {
            dx = -3;
        } else if (rightIsDown) {
            dx = 3;
        } else if (!(leftIsDown) || !(rightIsDown)) {
            dx = 0;
        }

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= Commons.WIDTH - imageWidth * 2) {
            x = Commons.WIDTH - imageWidth * 2;
        }
    }

    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // 다른 키를 떼지 않고 누르고, 누르자마자 누르자마자 누르고 있던 반대 방향키를 떼면 패들이 멈춤.
        // 중복 입력이 안되는 거 같음. a키 누르다가 b 누르고 b를 떼면 a는 눌러져 있지만 b로 인한 release 이벤트로 패들속도는 0이
        // 돼있음. 천천히 수정해보면 될듯
        if (key == KeyEvent.VK_LEFT) {
            leftIsDown = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightIsDown = true;
        }
    }

    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftIsDown = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightIsDown = false;
        }
    }

    private void resetState() {
        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }
}
