package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class Utils {
    // For all the Useful Methods we will use.

    // Attributes to connect to the DB
    private static Connection connect = null;
    private static String url = "jdbc:mysql://localhost:3306/daycare";
    private static String user = "root";
    private static String password = "";

    // Change of Scene:
    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;

        try {
            root = FXMLLoader.load(Utils.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    // Connect to Database:
    private static void connection(){
        try {
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Success!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // For testing if the Connection worked!
    public static void connect(){
        connection();
    }
}
