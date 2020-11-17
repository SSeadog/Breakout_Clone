package com.seadog;

import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.*;

public class Stage extends JFrame {
    private int lastStage;

    public Stage(int x, int y) {
        setTitle("Stage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocation(x, y);
        setContentPane(new MyPane());
        setVisible(true);

        lastStage = getStageCount();
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
            st1.setSize(120, 100);
            st1.setLocation(160, 350);
            st1.addMouseListener(new MyMouse(st1));
            add(st1);

            JLabel st2 = new JLabel("2");
            st2.setFont(new Font("Arial", Font.BOLD, 100));
            st2.setSize(120, 100);
            st2.setLocation(340, 350);
            st2.addMouseListener(new MyMouse(st2));
            add(st2);

            JLabel st3 = new JLabel("3");
            st3.setFont(new Font("Arial", Font.BOLD, 100));
            st3.setSize(120, 100);
            st3.setLocation(160, 550);
            st3.addMouseListener(new MyMouse(st3));
            add(st3);

            JLabel st4 = new JLabel("4");
            st4.setFont(new Font("Arial", Font.BOLD, 100));
            st4.setSize(120, 100);
            st4.setLocation(340, 550);
            st4.addMouseListener(new MyMouse(st4));
            add(st4);

            JLabel left = new JLabel("<");
            left.setFont(new Font("Arial", Font.PLAIN, 100));
            left.setSize(100, 100);
            left.setLocation(25, 450);
            left.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (!(st1.getText().equals("1"))) {
                        JLabel[] stn = { st1, st2, st3, st4 };
                        for (JLabel st : stn) {
                            st.setText(Integer.toString(Integer.parseInt(st.getText()) - 4));
                            if (Integer.parseInt(st.getText()) <= lastStage) {
                                st.setVisible(true);
                            }
                        }
                    }
                }
            });
            add(left);

            JLabel right = new JLabel(">");
            right.setFont(new Font("Arial", Font.PLAIN, 100));
            right.setSize(100, 100);
            right.setLocation(500, 450);
            right.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (Integer.parseInt(st4.getText()) <= lastStage) {
                        JLabel[] stn = { st1, st2, st3, st4 };
                        for (JLabel st : stn) {
                            st.setText(Integer.toString(Integer.parseInt(st.getText()) + 4));
                            if (Integer.parseInt(st.getText()) >= lastStage) {
                                st.setVisible(false);
                            }
                        }
                    }

                }
            });
            add(right);
        }
    }

    class MyMouse extends MouseAdapter {
        JLabel lbl;

        public MyMouse(JLabel lbl) {
            this.lbl = lbl;
        }

        public void mousePressed(MouseEvent e) {
            System.out.println(lbl.getText());
            int x = getX();
            int y = getY();
            System.out.println(x + ", " + y);
            dispose();
            var game = new Breakout(lbl.getText(), x, y);
            game.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Stage(100, 0);
    }
}
