package com.seadog;

import java.awt.Image;
import java.awt.Rectangle;

// x,y 그리고 이미지의 크기에 관한 정보들을 가지고 있고 그걸 출력해주는 클래스
public class Sprite {

    int x; // x 좌표 저장
    int y; // y 좌표 저장
    int imageWidth;
    int imageHeight;
    Image image;

    protected void setX(int x) {
        this.x = x;
    }

    // x는 디폴트 접근자인데 얘도 디폴트로 두면 무슨 의도?
    int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    int getY() {
        return y;
    }

    int getImageWidth() {
        return imageWidth * 2;
    }

    int getImageHeight() {
        return imageHeight * 2;
    }

    Image getImage() {
        return image;
    }

    Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null) * 2, image.getHeight(null) * 2);
    }

    void getImageDimensions() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
}
