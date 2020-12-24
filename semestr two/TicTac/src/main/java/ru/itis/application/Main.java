package ru.itis.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends javafx.application.Application {


    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/Main.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));

        primaryStage.setTitle("Моя Игруха");
        primaryStage.setResizable(false);




        primaryStage.show();
    }
}
