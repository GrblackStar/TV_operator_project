package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    @FXML
    private Button bt_plans_client_menu;
    @FXML
    private Button bt_bills_client_menu;
    @FXML
    private Button bt_pay_client_menu;
    @FXML
    private Button bt_logout_client_menu;
    @FXML
    private Label lb_username;




    public void setUserUsername(String username){
        lb_username.setText(username);
    }






    // the method is called when we want to interact with the stuff in the fxml file
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bt_logout_client_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "Log in!");
            }
        });
        bt_plans_client_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneClient(event, "view-plans-client", "PLANS MENU", lb_username.getText());
            }
        });
        bt_bills_client_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneClient(event, "view-all-bills-client", "BILLS MENU", lb_username.getText());
            }
        });
        bt_pay_client_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneClient(event, "pay-bill-client", "PLANS", lb_username.getText());
            }
        });

    }



}
