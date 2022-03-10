package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button backToLogInButton;
    @FXML
    private Button signUpAndEnterButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backToLogInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "main.fxml", "Log In");
            }
        });

        signUpAndEnterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        });
    }
}
