package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInAdminController implements Initializable {
    @FXML
    private TextField tf_username_admin;
    @FXML
    private TextField tf_password_admin;
    @FXML
    private TextField tf_authcode_admin;
    @FXML
    private Button bt_back_sign_in_admin;
    @FXML
    private Button bt_ok_sign_in_admin;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back_sign_in_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "WELCOME!");
            }
        });
        bt_ok_sign_in_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInAdmin(event, tf_username_admin.getText(), tf_password_admin.getText(), tf_authcode_admin.getText());
            }
        });

    }
}
