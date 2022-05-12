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

public class RegAsAdminController implements Initializable {
    @FXML
    private TextField tf_first_name_admin;
    @FXML
    private TextField tf_last_name_admin;
    @FXML
    private TextField tf_egn_admin;
    @FXML
    private TextField tf_phone_admin;
    @FXML
    private TextField tf_username_admin_reg;
    @FXML
    private TextField tf_password_admin_reg;
    @FXML
    private TextField tf_authcode_admin_reg;
    @FXML
    private Button bt_back_reg_admin;
    @FXML
    private Button bt_ok_reg_admin;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back_reg_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "WELCOME!");
            }
        });
        bt_ok_reg_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_first_name_admin.getText().trim().isEmpty() &&
                        !tf_last_name_admin.getText().trim().isEmpty() &&
                        !tf_egn_admin.getText().trim().isEmpty() &&
                        !tf_phone_admin.getText().trim().isEmpty() &&
                        !tf_username_admin_reg.getText().trim().isEmpty() &&
                        !tf_password_admin_reg.getText().trim().isEmpty() &&
                        !tf_authcode_admin_reg.getText().trim().isEmpty()){
                    DBUtils.registerAdmin(event, tf_first_name_admin.getText(),
                            tf_last_name_admin.getText(), tf_egn_admin.getText(),
                            tf_phone_admin.getText(), tf_username_admin_reg.getText(),
                            tf_password_admin_reg.getText(), tf_authcode_admin_reg.getText());
                    DBUtils.regStatusAdmin(tf_username_admin_reg.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

    }
}
