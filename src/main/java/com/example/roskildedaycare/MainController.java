package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // FXML components we will interact with:
    @FXML
    private Button logInButton;
    @FXML
    private Button signUpButton;
    // Button for skipping the Log In Process. Development time only.
    @FXML
    private Button buttonForEntering;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "signUp.fxml", "Sign Up");
            }
        });

        // Method when the Log In Button gets pressed.
        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.loginUser(actionEvent, usernameTextField.getText(), passwordPasswordField.getText());
                usernameTextField.setText("");
                passwordPasswordField.setText("");
                usernameTextField.requestFocus();
            }
        });

        // Method for the button for skipping the Log In.
        buttonForEntering.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        });
    }
}