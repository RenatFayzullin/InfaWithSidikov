package ru.itis.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static ru.itis.controllers.MainController.*;

//public class Tile extends StackPane {
//
//    public static boolean turnX = true;
//
//    private Text text = new Text();
//
//    public Tile() {
//        Rectangle border = new Rectangle(200, 200);
//        border.setFill(null);
//        border.setStroke(Color.BLACK);
//
//        text.setFont(Font.font(86));
//
//        setAlignment(Pos.CENTER);
//        getChildren().addAll(border, text);
//
//        setOnMouseClicked(event -> {
//
//            if (!playable) {
//                return;
//            }
//
//            if (event.getButton() == MouseButton.PRIMARY) {
//                if (turnX) {
//                    drawX();
//                    turnX = false;
//                    chekState();
//                }
//            } else if (event.getButton() == MouseButton.SECONDARY) {
//                if (!turnX) {
//                    drawO();
//                    turnX = true;
//                    chekState();
//                }
//            }
//        });
//    }
//
//    public String getValue() {
//        return text.getText();
//    }
//
//    private void drawX() {
//        text.setText("X");
//    }
//
//    private void drawO() {
//        text.setText("O");
//    }
//
//    public double getCenterX() {
//        return getTranslateX() + 100;
//    }
//    public double getCenterY() {
//        return getTranslateY() + 100;
//    }
//
//
//
//}
