package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatePackageController implements Initializable {

    @FXML
    private TextField tf_name;
    @FXML
    private Button bt_add;
    @FXML
    private Button bt_back;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_name.getText().trim().isEmpty()) {
                    DBUtils.checkIfPackageExists(event, tf_name.getText());
                    DBUtils.changeScenePackage(event, "add-channels-to-package.fxml", null, tf_name.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information!");
                    alert.show();
                }
            }
        });

    }
}
