package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Button bt_signin_client;
    @FXML
    private Button bt_signin_admin;
    @FXML
    private Button bt_reg_client;
    @FXML
    private Button bt_reg_admin;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_signin_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // change scene to sign in client
                DBUtils.changeSceneToMain(event, "sign-in-client.fxml", "Please log in!");
            }
        });
        bt_signin_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sign-in-admin.fxml", "Please log in!");
            }
        });
        bt_reg_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "reg-as-client.fxml", "Signing up!");
            }
        });
        bt_reg_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "reg-as-admin.fxml", "Signing up!");
            }
        });

    }
}
