package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button goBackButton;
    @FXML
    private Button createUserButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    AnchorPane signUpAnchorPane;
    @FXML
    private PasswordField confirmPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "main.fxml", "Log In",457,360);
            }
        });

        createUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!usernameTextField.getText().trim().isEmpty() && !passwordField.getText().trim().isEmpty()){
                    Utils.signUp(actionEvent, usernameTextField.getText(), passwordField.getText(), confirmPasswordField.getText());
                }
                else {
                    System.out.println("Please fill all the info");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all the info");
                    alert.show();
                }

                usernameTextField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                usernameTextField.requestFocus();


            }
        });


    }
}
