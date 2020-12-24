package ru.itis.socket;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.MainController;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveMessageTask extends Task<Void> {
    private BufferedReader fromServer;
    private MainController controller;

    public ReceiveMessageTask(BufferedReader fromServer, MainController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    if (messageFromServer.charAt(0) == 'x') {
                        String[] data = messageFromServer.split(" ");
                        Platform.runLater(() ->
                                controller.movePlayerX(Integer.parseInt(data[1]), Integer.parseInt(data[2]))
                        );
                    }
                }
                if (messageFromServer != null) {
                    if (messageFromServer.charAt(0) == 'o') {
                        String[] data = messageFromServer.split(" ");
                        Platform.runLater(() ->
                                controller.movePlayerO(Integer.parseInt(data[1]), Integer.parseInt(data[2]))
                        );
                    }
                }
                if (messageFromServer != null) {
                    if (messageFromServer.equals("chekState")) {
                        Platform.runLater(() ->
                                controller.chekState()
                        );
                    }
                }
            } catch (IOException e){
                throw new IllegalStateException(e);
            }

        }
    }
}
