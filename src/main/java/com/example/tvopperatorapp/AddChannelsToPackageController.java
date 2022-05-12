package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddChannelsToPackageController implements Initializable {

    @FXML
    private TextField tf_channel_name;
    @FXML
    private Button bt_next;
    @FXML
    private Button bt_done;
    @FXML
    private Label lb_package_name;

    public void setPackageName(String username){
        lb_package_name.setText(username);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_channel_name.getText().trim().isEmpty()){
                    DBUtils.checkIfChannelExists(event, lb_package_name.getText(), tf_channel_name.getText());
                    DBUtils.changeScenePackage(event, "add-channels-to-package.fxml", null, lb_package_name.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information!");
                    alert.show();
                }

            }
        });
        bt_done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_channel_name.getText().trim().isEmpty()){
                    DBUtils.checkIfChannelExists(event, lb_package_name.getText(), tf_channel_name.getText());
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
