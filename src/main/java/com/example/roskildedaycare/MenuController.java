package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable{
    // todo: implements all the other buttons
    @FXML Button childListsButton;
    @FXML Button addChildButton;

    public void initialize(URL url, ResourceBundle resourceBundle){
        childListsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "childrenLists.fxml", "Lists");
            }
        });

        addChildButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "addChild.fxml", "Add new child");
            }
        });

    };

}
