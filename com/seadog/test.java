package com.seadog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
    public static void main(String[] args) {
        int c = 1;
        Path path = Paths.get("C:\\documents\\Breakout_Clone\\stages\\4.txt");
        // System.out.println(path.getFileName());
        try {
            while (true) {
                File file = new File("stages/" + c + ".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                line = br.readLine();
                System.out.println(c + ".txt");
                c++;
            }
        } catch (Exception e) {
            System.out.println(c + "error");
        }
    }
}
