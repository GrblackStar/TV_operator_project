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

public class AddProviderController implements Initializable {
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_deal_date;
    @FXML
    private TextField tf_end_date;
    @FXML
    private TextField tf_channel_name;
    @FXML
    private TextField tf_channel_category;
    @FXML
    private Button bt_done_add_provider;
    @FXML
    private Button bt_back_add_provider;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back_add_provider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_done_add_provider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_name.getText().trim().isEmpty() &&
                        !tf_channel_name.getText().trim().isEmpty() &&
                        !tf_channel_category.getText().trim().isEmpty() &&
                        !tf_deal_date.getText().trim().isEmpty() &&
                        !tf_end_date.getText().trim().isEmpty() &&
                        !tf_id.getText().trim().isEmpty()){
                    DBUtils.addProvider(event, tf_name.getText(), tf_id.getText(), tf_deal_date.getText(),
                            tf_end_date.getText(), tf_channel_name.getText(), tf_channel_category.getText());
                    DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to!");
                    alert.show();
                }
            }
        });

    }
}
