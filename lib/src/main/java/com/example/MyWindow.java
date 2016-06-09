package com.example;


import javax.swing.JFrame;

/**
 * Created by NTHUCS on 2016/6/9.
 */
public class MyWindow{
    public static void main(String[] args) {
        Server server = new Server(8000);
        server.setLocation(200, 100);
        server.setSize(300,200);
        server.setResizable(false);
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        server.setVisible(true);
        server.runForever();
    }
}
