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

public class ChannelPriceChangeController implements Initializable {
    @FXML
    private Button bt_cancel_channel_change;
    @FXML
    private Button bt_ok_channel_change;
    @FXML
    private TextField tf_name_channel_change;
    @FXML
    private TextField tf_price_channel_change;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_cancel_channel_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_ok_channel_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_name_channel_change.getText().trim().isEmpty() && !tf_price_channel_change.getText().trim().isEmpty()){
                    DBUtils.changePrice(event, tf_name_channel_change.getText(), tf_price_channel_change.getText());
                    DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
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
