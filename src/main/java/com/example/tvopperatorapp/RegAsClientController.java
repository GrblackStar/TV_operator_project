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

public class RegAsClientController implements Initializable {
    @FXML
    private TextField tf_first_name_client;
    @FXML
    private TextField tf_last_name_client;
    @FXML
    private TextField tf_egn_client;
    @FXML
    private TextField tf_phone_client;
    @FXML
    private TextField tf_username_client_reg;
    @FXML
    private TextField tf_password_client_reg;
    @FXML
    private Button bt_back_reg_client;
    @FXML
    private Button bt_ok_reg_client;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back_reg_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "WELCOME!");
            }
        });
        bt_ok_reg_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_first_name_client.getText().trim().isEmpty() &&
                        !tf_last_name_client.getText().trim().isEmpty() &&
                        !tf_egn_client.getText().trim().isEmpty() &&
                        !tf_phone_client.getText().trim().isEmpty() &&
                        !tf_username_client_reg.getText().trim().isEmpty() &&
                        !tf_password_client_reg.getText().trim().isEmpty()) {
                    DBUtils.registerClient(event, tf_first_name_client.getText(), tf_last_name_client.getText(),
                            tf_egn_client.getText(), tf_phone_client.getText(), tf_username_client_reg.getText(),
                            tf_password_client_reg.getText());
                    DBUtils.regStatusClient(tf_username_client_reg.getText());
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
