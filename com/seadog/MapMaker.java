package com.seadog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MapMaker extends JFrame {
    int[][] Bricks = new int[100][2];
    int cur = -1;

    public MapMaker(int x, int y) {
        setTitle("맵 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocation(x, y);
        setContentPane(new MyPanel());
        setVisible(true);
    }

    int getStageCount() {
        int cur = 0;
        try {
            while (true) {
                File file = new File("stages/" + cur + ".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();
                cur++;
                br.close();
            }
        } catch (Exception e) {
            System.out.println(cur + "error");
        }
        return cur;
    }

    class MyPanel extends JPanel {
        public MyPanel() {
            setLayout(null);
            addMouseListener(new MyMouseAdapter());
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new MapMaker(100, 0);
    }

    class MyMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            // 추후에 겹쳐서 만들어지는 경우를 생각해야 할듯
            int x = e.getX();
            int y = e.getY();

            add(new Brick(x, y));
            repaint();

            if (cur == 9) {
                System.out.println("Writing");
                try {
                    int c = getStageCount();
                    FileWriter fw = new FileWriter("stages/" + c + ".txt");
                    for (int b = 0; b < 10; b++) {
                        fw.write(Bricks[b][0] + "," + Bricks[b][1] + "\n");
                    }
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Writing End");
            }
        }
    }

    class Brick extends JLabel {
        public Brick(int x, int y) {
            setText("Brick");
            setSize(80, 20);
            setLocation(x, y);
            cur++;
            Bricks[cur][0] = x;
            Bricks[cur][1] = y;
        }
    }

}