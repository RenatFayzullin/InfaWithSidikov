package ru.itis.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ru.itis.socket.ReceiveMessageTask;
import ru.itis.socket.SocketClient;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MainController implements Initializable {

    private SocketClient client;

    private boolean playable = true;
    private final List<Combo> combos = new ArrayList<>();

    private final Tile[][] board = new Tile[3][3];

    @FXML
    private AnchorPane pane;

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new SocketClient("localhost", 7777);
        ReceiveMessageTask receiveMessageTask = new ReceiveMessageTask(client.getFromServer(), this);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(receiveMessageTask);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                pane.getChildren().add(tile);

                board[j][i] = tile;

            }
        }

        //horizontal
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }

        //vertical
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }

        //diagonals
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));


    }

    public void movePlayerX(int x, int y) {
        board[y][x].drawX();

    }

    public void movePlayerO(int x, int y) {
        board[y][x].drawO();

    }

    public void chekState() {
        for (Combo combo : combos) {
            if (combo.isComplete()) {
                playable = false;
                playWinAnimation(combo);
                break;
            }
        }
    }

    public void playWinAnimation(Combo combo) {
        Line line = new Line();
        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndX(combo.tiles[0].getCenterX());
        line.setEndY(combo.tiles[0].getCenterY());

        pane.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3),
                new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
        timeline.play();
    }

    public class Tile extends StackPane {

        private Text text = new Text();

        public Tile(int x, int y) {
            Rectangle border = new Rectangle(200, 200);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(86));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {

                if (!playable) {
                    return;
                }

                if (event.getButton() == MouseButton.PRIMARY) {

                    drawX();
                    client.sendMassage("x " + x + " " + y);
                    client.sendMassage("chekState");
                    chekState();

                }
                else if (event.getButton() == MouseButton.SECONDARY) {

                    drawO();
                    client.sendMassage("o " + x + " " + y);
                    client.sendMassage("chekState");
                    chekState();

                }
            });
        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() {
            text.setText("X");
        }

        private void drawO() {
            text.setText("O");
        }

        public double getCenterX() {
            return getTranslateX() + 100;
        }

        public double getCenterY() {
            return getTranslateY() + 100;
        }

    }


}
