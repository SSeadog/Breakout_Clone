package com.seadog;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Breakout extends JFrame {
    public Breakout(int x, int y) {
        initUI(x, y);
    }

    private void initUI(int x, int y) {
        add(new Board());
        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(x, y);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var game = new Breakout(100, 100);
            game.setVisible(true);
        });
    }
}
