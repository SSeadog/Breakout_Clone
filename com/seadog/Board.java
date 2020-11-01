package com.seadog;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        gameInit();
    }

    private void gameInit() {
        // 블록 정보를 담을 배열 생성
        bricks = new Brick[Commons.N_OF_BRICKS];

        // ball, paddle 인스턴스 생성
        ball = new Ball();
        paddle = new Paddle();

        int k = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }

        timer = new Timer(Commons.PERIOD, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        // ball, paddle 그리기
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), paddle.getImageWidth(), paddle.getImageHeight(),
                this);

        // 블록 그리기
        for (int i = 0; i < Commons.N_OF_BRICKS; i++) {
            if (!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {
        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message, (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.WIDTH / 2);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {
        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {
        inGame = false;
        timer.stop();
    }

    private void checkCollision() {
        if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) { // 공이 아랫 부분을 벗어나면 종료
            stopGame();
        }

        for (int i = 0, j = 0; i < Commons.N_OF_BRICKS; i++) {
            if (bricks[i].isDestroyed()) {
                j++;
            }

            if (j == Commons.N_OF_BRICKS) { // 블록들을 다 부셨으면 종료
                message = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {
            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            // 패들을 5등분해서 부딪히는 위치에 따라 공을 다른 각도로 날림
            if (ballLPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        for (int i = 0; i < Commons.N_OF_BRICKS; i++) {
            if ((ball.getRect()).intersects(bricks[i].getRect())) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        // 공의 오른쪽이 블록에 닿으면 x방향 -1로 바꿈
                        ball.setXDir(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {
                        // 공의 오른쪽이 블록에 닿으면 x방향 -1로 바꿈
                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }
                    bricks[i].setDestroyed(true);
                }

            }
        }
    }

}
