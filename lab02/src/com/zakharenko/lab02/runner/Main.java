package com.zakharenko.lab02.runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/sample.fxml")));
        primaryStage.setTitle("lab02 Захаренко 051007");
        primaryStage.setScene(new Scene(root, 1490, 742));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
