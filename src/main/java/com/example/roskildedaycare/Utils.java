package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Utils {
    // For all the Useful Methods we will use.

    // Attributes to connect to the DB
    private static Connection connect = null;
    private static String url = "jdbc:mysql://localhost:3306/daycare";
    private static String user = "root";
    private static String password = "";

    // Attributes to use the DB
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

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

    // For Logging In the User
    public static void loginUser(ActionEvent event, String username, String password){
        try {
            connection();
            preparedStatement = connect.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");

                    if (retrievedPassword.equals(password)){
                        changeScene(event, "menu.fxml", "Menu");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords don't match");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // For Populating the List in ChildrenList
    public static ArrayList<String> childrenList(){

        ArrayList<String> children = new ArrayList<>();

        try {
            connection();
            preparedStatement = connect.prepareStatement("SELECT first_name, last_name FROM children");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String retrievedFirstName = resultSet.getString("first_name");
                String retrievedLastName = resultSet.getString("last_name");
                String fullName = retrievedLastName + ", " + retrievedFirstName;

                children.add(fullName);
                }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return children;

    }

    // For testing if the Connection worked!
    public static void connect(){
        connection();
    }

    // Closing of connections:
    public static void closeConnection(){
        if (connect != null){
            try {
                connect.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
