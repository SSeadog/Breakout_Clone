package com.seadog;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Stage extends JFrame {
    public Stage(int x, int y) {
        setTitle("Stage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocation(x, y);
        setContentPane(new MyPane());
        setVisible(true);
    }

    public class MyPane extends JPanel {
        public MyPane() {
            setLayout(null);

        }
    }
}
