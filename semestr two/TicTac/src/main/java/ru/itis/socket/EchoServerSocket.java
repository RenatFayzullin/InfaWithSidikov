package ru.itis.socket;

import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSocket extends Task<Void> {

    private ServerSocket server;
    private BufferedReader fromClient;
    private BufferedReader fromClient2;
    private PrintWriter toClient;
    private PrintWriter toClient2;
    private static final int PORT = 7777;
    private Socket client;
    private Socket client2;

    public void start(int port) {

        try {
            server = new ServerSocket(port);
            Socket client = server.accept();
            Socket client2 = server.accept();
            System.out.println("client ready");


            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(client.getOutputStream(), true);

            fromClient2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            toClient2 = new PrintWriter(client2.getOutputStream(), true);

            toClient.println("1");
            toClient.println("2");

            new Thread(game1).start();
            new Thread(game2).start();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    Runnable game1 = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    String messageFromClient = fromClient.readLine();
                    while (messageFromClient != null) {
                        toClient2.println(messageFromClient);
                        toClient2.flush();
                        messageFromClient = fromClient.readLine();
                    }

                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    };

    Runnable game2 = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    String messageFromClient = fromClient2.readLine();
                    while (messageFromClient != null) {
                        toClient.println(messageFromClient);
                        toClient.flush();
                        messageFromClient = fromClient2.readLine();
                    }

                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    };

    @Override
    protected Void call() throws Exception {
        this.start(PORT);
        return null;
    }
}
