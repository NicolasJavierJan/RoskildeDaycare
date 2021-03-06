package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChildInformationController implements Initializable {
    @FXML
    private Label childNameLabel;
    @FXML
    private Label childBirthLabel;
    @FXML
    private Label extraInfoLabel;
    @FXML
    private Label parentNameLabel;
    @FXML
    private Label parentPhoneLabel;
    @FXML
    private Label parentEmailLabel;
    @FXML
    private Button goBackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "childrenLists.fxml", "Lists", 600,450);
            }
        });
    }

    public void setChildInformation(String childName, String childBirth, String extraInfo, String parentName, String parentPhone, String parentMail){
        childNameLabel.setText(childName);
        childBirthLabel.setText(childBirth);
        extraInfoLabel.setText(extraInfo);
        parentNameLabel.setText(parentName);
        parentPhoneLabel.setText(parentPhone);
        parentEmailLabel.setText(parentMail);

    }
}
