package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteQuestionClientController implements Initializable {
    @FXML
    private Label lb_egn;
    @FXML
    private Button bt_yes_delete_client;
    @FXML
    private Button bt_no_delete_client;


    public void setUserEgn(String egn){
        lb_egn.setText(egn);
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_no_delete_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAdminClient(event, "admin-client-menu.fxml", "CLIENT MENU", lb_egn.getText());
            }
        });
        bt_yes_delete_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.deleteClient(event, lb_egn.getText());
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });

    }
}
