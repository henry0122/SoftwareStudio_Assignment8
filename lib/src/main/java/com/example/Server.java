package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame{
    private ServerSocket serverSocket;
    private JTextArea text;
    private JScrollPane scrollPane;

    public Server(int portNum) {
        try {
            text = new JTextArea();
            text.setEditable(false);
            add(text);
            this.serverSocket = new ServerSocket(portNum);
            System.out.printf("Server starts listening on port %d.\n", portNum);
            InetAddress IP = InetAddress. getLocalHost();
            System.out.println("Server starts to listen on ip:" + IP.getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runForever() {
        System.out.println("Server starts waiting for client.");
        // Create a loop to make server wait for client forever (unless you stop it)
        // Make sure you do create a connectionThread and add it into 'connections'
        while (true) {
            try {
                Socket connectionToClient = this.serverSocket.accept();
                System.out.println("Get connection from client "
                        + connectionToClient.getInetAddress() + ":"
                        + connectionToClient.getPort());
                ConnectionThread connThread = new ConnectionThread(connectionToClient);
                connThread.start();
            } catch (BindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // Define an inner class (class name should be ConnectionThread)

    class ConnectionThread extends Thread {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ConnectionThread(Socket socket) {
            this.socket = socket;
            try {
                this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            while (true) {
                try {
                    String line = reader.readLine();
                    text.setText("The result from app is " + line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
