package com.seadog;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Start extends JFrame {

    public Start() {
        setTitle("SBreakout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocation(300, 0);
        setContentPane(new MyPane());
        setVisible(true);

    }

    public static void main(String[] args) {
        new Start();
    }

    public class MyPane extends JPanel {
        public MyPane() {
            setLayout(null);
            setBackground(Color.LIGHT_GRAY);

            add(new Button1("Start"));
            add(new Button2("Stack Block"));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.ITALIC, 750));
            g.drawString("S", 200, 150);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.ITALIC, 70));
            g.drawString("SBreakout", 150, 225);
        }
    }

    public class Button1 extends JButton {
        public Button1(String s) {
            setText(s);
            setSize(150, 50);
            setLocation(225, 350);
            this.addMouseListener(new MyMouse());
        }
    }

    class MyMouse extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            System.out.println("1 Clicked");
            int x = getX();
            int y = getY();
            System.out.println(x + ", " + y);
            dispose();
            var game = new Stage(x, y);
            game.setVisible(true);
        }
    }

    public class Button2 extends JButton {
        public Button2(String s) {
            setText(s);
            setSize(150, 50);
            setLocation(225, 550);
            this.addMouseListener(new MyMouse2());
        }
    }

    class MyMouse2 extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            System.out.println("2 Clicked");
            int x = getX();
            int y = getY();
            dispose();
            var game = new MapMaker(x, y);
            game.setVisible(true);
        }
    }
}
