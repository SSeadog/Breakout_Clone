package com.seadog;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Breakout extends JFrame {
    private String stage;
    private int x, y;

    public Breakout(String stage, int x, int y) {
        this.stage = stage;
        this.x = x;
        this.y = y;
        initUI();
    }

    private void initUI() {
        add(new Board(stage));
        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(x, y);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var game = new Breakout("Bricks", 100, 0);
            game.setVisible(true);
        });
    }
}
