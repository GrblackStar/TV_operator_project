package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInClientController implements Initializable {
    @FXML
    private Button bt_back_sign_in_client;
    @FXML
    private Button bt_ok_sign_in_client;
    @FXML
    private TextField tf_username_client;
    @FXML
    private TextField tf_password_client;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_ok_sign_in_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInClient(event, tf_username_client.getText(), tf_password_client.getText());
            }
        });
        bt_back_sign_in_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "WELCOME!");
            }
        });


    }
}
