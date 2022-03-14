package com.example.roskildedaycare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    // Skeleton of the App. Creates a Scene and puts the Log In Window in it.
    // Launches Project.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
        Utils.connect();
    }

    public static void main(String[] args) {
        launch();
    }
}