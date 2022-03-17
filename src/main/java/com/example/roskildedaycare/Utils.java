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
import java.util.Date;

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

    // For sign up
    private static PreparedStatement psCheckExists = null;
    private static PreparedStatement psInsert = null;

    //For add child
    private static PreparedStatement psCheckExists1 = null;
    private static ResultSet resultSet1 = null;

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

    // Method to sign up a new user: check if the username already taken, and if the password and confirm password match
    public static void signUp(ActionEvent event, String username, String password, String confirmPassword) {
        try {

            connection();
            psCheckExists = connect.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckExists.setString(1, username);
            resultSet = psCheckExists.executeQuery();

            if (!password.equals(confirmPassword)) {
                System.out.println("The two password don't match, try again");
                Alert alert = new Alert(Alert.AlertType.ERROR, "The two passwords don't match, try again");
                alert.show();
            } else if (resultSet.isBeforeFirst()) {

                System.out.println("Username already taken, try again");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User with those credential already exists, try again with a different username ");
                alert.show();


            } else {

                psInsert = connect.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "menu.fxml", "Menu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
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

    public static void addChild(ActionEvent event, String parentFirstName, String parentLastName, String parentPhoneNumber, String parentEmail,
                                String childFirstName, String childLastName, String childBirthDate, String childRelevantInfo){

        try {
            connection();

            // Check if parent exists
            psCheckExists1 = connect.prepareStatement("SELECT * FROM parents WHERE first_name = ? AND last_name = ? AND phone_number = ? AND e_mail = ?");
            psCheckExists1.setString(1, parentFirstName);
            psCheckExists1.setString(2, parentLastName);
            psCheckExists1.setString(3, parentPhoneNumber);
            psCheckExists1.setString(4, parentEmail);
            resultSet1 = psCheckExists1.executeQuery();

            // Check if child Exists
            psCheckExists = connect.prepareStatement("SELECT * FROM children WHERE first_name = ? AND last_name = ? AND birth_date = ? AND extra_info = ?");
            psCheckExists.setString(1, childFirstName);
            psCheckExists.setString(2, childLastName);
            psCheckExists.setString(3, childBirthDate.toString());
            psCheckExists.setString(4, childRelevantInfo);
            resultSet = psCheckExists.executeQuery();

            // check child exists
            if (resultSet.isBeforeFirst()) {

                System.out.println("Child already exists, try again");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User with those credential already exists, try again with a different username ");
                alert.show();
            }
            //check parent exists
            else if (resultSet1.isBeforeFirst()){

                System.out.println("Parent already exist, try again");
                Alert alert = new Alert(Alert.AlertType.ERROR,"Parent already exist, try again");
                alert.show();

            } else {

                //queries for Insert parent
                PreparedStatement psInsert1 = null;
                psInsert1 = connect.prepareStatement("INSERT INTO parents(first_name, last_name, phone_number, e_mail) VALUES(?, ?, ?, ?)");
                psInsert1.setString(1, parentFirstName);
                psInsert1.setString(2, parentLastName);
                psInsert1.setString(3, parentPhoneNumber);
                psInsert1.setString(4, parentEmail);
                psInsert1.executeUpdate();
                System.out.println("Successful queries 'INSERT INTO' for parent info ");

                //Queries for retrieving parent_id(FK) and tore it in a variable
                PreparedStatement psSelectParent_id= null;
                psSelectParent_id = connect.prepareStatement("SELECT id FROM parents WHERE first_name = ? AND last_name = ? AND phone_number = ? AND e_mail= ?");
                psSelectParent_id.setString(1, parentFirstName);
                psSelectParent_id.setString(2, parentLastName);
                psSelectParent_id.setString(3, parentPhoneNumber);
                psSelectParent_id.setString(4, parentEmail);
                ResultSet resultSet2 = psSelectParent_id.executeQuery();
                if (resultSet2.next()){
                    System.out.println(resultSet2.getString("id"));
                    System.out.println("Finally it works");
                }

                // queries for Insert child
                psInsert = connect.prepareStatement("INSERT INTO children(first_name, last_name, birth_date, extra_info, parent_id) VALUES (?, ?, ?, ?, ?)");
                psInsert.setString(1, childFirstName);
                psInsert.setString(2, childLastName);
                psInsert.setString(3, childBirthDate);
                psInsert.setString(4, childRelevantInfo);
                psInsert.setString(5, resultSet2.getString("id"));
                psInsert.executeUpdate();
                System.out.println("Successful queries 'INSERT INTO' for child info ");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Operation successful :)");
                alert.show();


            }
        } catch (SQLException e){
        e.printStackTrace();
    } finally {
        closeConnection();
    }
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

    public static void seeInformation(ActionEvent event, String fxmlFile, String title, String childName){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxmlFile));
            root = loader.load();
            ChildInformationController childInformationController = loader.getController();


            // Separates the childName String;
            String[] split = childName.split(", ");
            String childLast = split[0];
            String childFirst = split[1];
            // Variables that are going to be passed for the child information screen
            String parentID = null;
            String extra = null;
            String childBirth = null;
            String parentFirst = null;
            String parentLast = null;
            String parentPhone = null;
            String parentMail = null;

            // Connecting to database and getting results
            try {
                // Connecting to Database to fetch information
                connection();
                // Getting information from children:
                preparedStatement = connect.prepareStatement("SELECT * FROM children WHERE first_name = ? and last_name = ?");
                preparedStatement.setString(1, childFirst);
                preparedStatement.setString(2, childLast);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    parentID = resultSet.getString("parent_id");
                    childBirth = resultSet.getString("birth_date");
                    extra = resultSet.getString("extra_info");
                }
                // Getting information from parents based on Parent ID
                preparedStatement = connect.prepareStatement("SELECT * FROM parents WHERE id = ?");
                preparedStatement.setString(1, parentID);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    parentFirst = resultSet.getString("first_name");
                    parentLast = resultSet.getString("last_name");
                    parentPhone = resultSet.getString("phone_number");
                    parentMail = resultSet.getString("e_mail");
                }
                String parentFullName = parentLast + ", " + parentFirst;

                // Sending all the information to the controller.
                childInformationController.setChildInformation(childName, childBirth, extra, parentFullName, parentPhone, parentMail);

            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
