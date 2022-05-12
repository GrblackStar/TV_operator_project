package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteQuestionProviderController implements Initializable {
    @FXML
    private Label lb_name_delete_provider;
    @FXML
    private Button bt_yes_delete_provider;
    @FXML
    private Button bt_no_delete_provider;

    public void setProviderName(String name){
        lb_name_delete_provider.setText(name);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_no_delete_provider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneProvider(event, "provider-menu.fxml", "PROVIDER MENU", lb_name_delete_provider.getText());
            }
        });
        bt_yes_delete_provider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.deleteProvider(event, lb_name_delete_provider.getText());
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });

    }
}
