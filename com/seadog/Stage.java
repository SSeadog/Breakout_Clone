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
            JLabel title = new JLabel("Stage");
            title.setFont(new Font("Arial", Font.BOLD, 100));
            title.setSize(300, 150);
            title.setLocation(150, 100);
            add(title);

            JLabel st1 = new JLabel("1");
            st1.setFont(new Font("Arial", Font.BOLD, 100));
            st1.setSize(100, 100);
            st1.setLocation(150, 350);
            add(st1);

            JLabel st2 = new JLabel("2");
            st2.setFont(new Font("Arial", Font.BOLD, 100));
            st2.setSize(100, 100);
            st2.setLocation(350, 350);
            add(st2);

            JLabel st3 = new JLabel("3");
            st3.setFont(new Font("Arial", Font.BOLD, 100));
            st3.setSize(100, 100);
            st3.setLocation(150, 550);
            add(st3);

            JLabel st4 = new JLabel("4");
            st4.setFont(new Font("Arial", Font.BOLD, 100));
            st4.setSize(100, 100);
            st4.setLocation(350, 550);
            add(st4);

            JLabel left = new JLabel("<");
            left.setFont(new Font("Arial", Font.PLAIN, 100));
            left.setSize(100, 100);
            left.setLocation(25, 450);
            add(left);

            JLabel right = new JLabel(">");
            right.setFont(new Font("Arial", Font.PLAIN, 100));
            right.setSize(100, 100);
            right.setLocation(500, 450);
            add(right);
        }
    }

    public static void main(String[] args) {
        new Stage(100, 0);
    }
}
