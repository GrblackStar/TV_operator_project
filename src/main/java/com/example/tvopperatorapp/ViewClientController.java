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

public class ViewClientController implements Initializable {
    @FXML
    private Button bt_back_view_client;
    @FXML
    private Button bt_ok_view_client;
    @FXML
    private TextField tf_enter_egn_view_client;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back_view_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_ok_view_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_enter_egn_view_client.getText().trim().isEmpty()){
                    DBUtils.viewClient(event, tf_enter_egn_view_client.getText());
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
