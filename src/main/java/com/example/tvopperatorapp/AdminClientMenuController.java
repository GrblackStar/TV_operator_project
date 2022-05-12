package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminClientMenuController implements Initializable {
    @FXML
    private Button bt_bills_admin_client;
    @FXML
    private Button bt_update_admin_client;
    @FXML
    private Button bt_delete_admin_client;
    @FXML
    private Button bt_back_admin_client_menu;
    @FXML
    private Label lb_egn;


    public void setUserEgn(String egn){
        lb_egn.setText(egn);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back_admin_client_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_update_admin_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAdminClient(event, "change-client-info.fxml", "UPDATE CLIENT", lb_egn.getText());
            }
        });
        bt_bills_admin_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAdminClient(event, "client-bills-admin.fxml", "CLIENT BILLS", lb_egn.getText());
            }
        });
        bt_delete_admin_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAdminClient(event, "delete-question-client.fxml", "DELETE CLIENT", lb_egn.getText());
            }
        });

    }
}
