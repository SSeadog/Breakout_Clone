package com.seadog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class test {
    public static void main(String[] args) {
        int c = 1;
        // stages 안에 txt가 몇까지 있는지 확인
        try {
            while (true) {
                File file = new File("stages/" + c + ".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();
                System.out.println(c + ".txt");
                c++;
            }
        } catch (Exception e) {
            System.out.println(c + "error");
        }
    }
}
