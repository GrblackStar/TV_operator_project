package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    @FXML
    private Button bt_add_client;
    @FXML
    private Button bt_view_client;
    @FXML
    private Button bt_channel_price_change;
    @FXML
    private Button bt_view_provider;
    @FXML
    private Button bt_package_admin_menu;
    @FXML
    private Button bt_logout_admin;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_add_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "add-client-contract.fxml", "ADD CLIENT");
            }
        });
        bt_view_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "view-client.fxml", "VIEW CLIENT");
            }
        });
        bt_channel_price_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "channel-price-change.fxml", "PRICE CHANGE");
            }
        });
        bt_view_provider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "view-provider.fxml", "Provider");
            }
        });
        bt_package_admin_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "create-package.fxml", "PACKAGES");
            }
        });
        bt_logout_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "Log in!");
            }
        });

    }
}
