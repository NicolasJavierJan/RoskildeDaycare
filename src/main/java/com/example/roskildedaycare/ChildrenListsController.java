package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChildrenListsController implements Initializable {

    @FXML Button child1InfoButton;
    @FXML Button goBackButton;


    public void initialize(URL url, ResourceBundle resourceBundle){

        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        });

    };


}
