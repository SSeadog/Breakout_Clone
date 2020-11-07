package com.seadog;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Breakout extends JFrame {
    public Breakout() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100, 100);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var game = new Breakout();
            game.setVisible(true);
        });
    }
}
