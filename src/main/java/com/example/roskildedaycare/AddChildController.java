package com.example.roskildedaycare;

import com.example.roskildedaycare.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddChildController implements Initializable {

    @FXML private Button goBackButton;
    @FXML private Button confirmButton;
    @FXML private TextField parentFirstNameField;
    @FXML private TextField parentLastNameField;
    @FXML private TextField parentPhoneNumberField;
    @FXML private TextField parentEmailField;
    @FXML private TextField childFirstNameField;
    @FXML private TextField childLastNameField;
    @FXML private TextField childBirthDateField;
    @FXML private TextField childRelevantInfoField;

    public void initialize(URL url, ResourceBundle resourceBundle){
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "Menu.fxml", "Menu", 450, 320);
            }
        });

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!parentFirstNameField.getText().trim().isEmpty() && !parentLastNameField.getText().trim().isEmpty()
                        && !parentPhoneNumberField.getText().trim().isEmpty() && !parentEmailField.getText().trim().isEmpty()
                        && !childFirstNameField.getText().trim().isEmpty() && !childLastNameField.getText().trim().isEmpty()
                        && !childBirthDateField.getText().trim().isEmpty()) {

                    Utils.addChild(actionEvent, parentFirstNameField.getText(), parentLastNameField.getText(), parentPhoneNumberField.getText(), parentEmailField.getText(),
                            childFirstNameField.getText(), childLastNameField.getText(), childBirthDateField.getText(), childRelevantInfoField.getText());

                    //Resetting al text fields to empty
                    //Parent's fields
                    parentFirstNameField.setText("");
                    parentLastNameField.setText("");
                    parentPhoneNumberField.setText("");
                    parentEmailField.setText("");
                    //Child's fields
                    childFirstNameField.setText("");
                    childLastNameField.setText("");
                    childBirthDateField.setText("");
                    childRelevantInfoField.setText("");

                } else {
                    System.out.println("Please fill in all info");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all info");
                    alert.show();
                }
            }

        });
    }

}
