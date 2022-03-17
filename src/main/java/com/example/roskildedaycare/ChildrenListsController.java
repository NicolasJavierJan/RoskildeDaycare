package com.example.roskildedaycare;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChildrenListsController implements Initializable {

    // List views and observableArrayLists
    @FXML
    ListView<String> childListView;
    ObservableList<String> childItems = FXCollections.observableArrayList();

    @FXML
    ListView<String> waitingChildrenListView;
    ObservableList<String> waitingChildrenItems = FXCollections.observableArrayList();

    @FXML Button goBackButton;
    @FXML
    private Button seeInformationButton;
    private String clickedLast;


    public void initialize(URL url, ResourceBundle resourceBundle){
        seeInformationButton.setDisable(true);

        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.changeScene(actionEvent, "menu.fxml", "Menu",450,320);
            }
        });

        // Calls the childrenList method in Utils to get the Children List
        ArrayList<String> children = Utils.childrenList();

        // Iterates over the list and puts 5 children in the Accepted Children List, the rest go
        // to waiting list.
        for (int i = 0; i < children.size(); i++){
            if (i <= 4){
                childItems.add(children.get(i));
            } else {
                waitingChildrenItems.add(children.get(i));
            }

        }

        // Set the items into the lists
        childListView.setItems(childItems);
        waitingChildrenListView.setItems(waitingChildrenItems);

        // Adds the name of the children that was clicked last to the clickedLast variable.
        childListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                seeInformationButton.setDisable(false);
                clickedLast = childListView.getSelectionModel().getSelectedItem();
            }
        });

        // Adds the name of the children that was clicked last to the clickedLast variable.
        waitingChildrenListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                seeInformationButton.setDisable(false);
                clickedLast =  waitingChildrenListView.getSelectionModel().getSelectedItem();
            }
        });

        // This method WILL open a new window with information about the last clicked children.
        seeInformationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Utils.seeInformation(actionEvent, "childInformation.fxml", "Child Information", clickedLast);
            }
        });

    };


}
