package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable{
    @FXML Button childListsButton;
    @FXML Button addChildButton;
    @FXML Button logOutButton;
    @FXML Label usernameLabel;

    public void initialize(URL url, ResourceBundle resourceBundle){

        childListsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "childrenLists.fxml", "Lists", 600,450);
            }
        });

        addChildButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "addChild.fxml", "Add Child", 540,523);
            }
        });

        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "main.fxml", "Login",457,360);
            }
        });

    }

    public void setUsernameLabel(String username){
       usernameLabel.setText("Hi " + username);
    }

}
