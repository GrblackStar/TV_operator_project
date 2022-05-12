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

public class ViewProviderController implements Initializable {
    @FXML
    private TextField tf_name_provider_view;
    @FXML
    private Button bt_search_provider_view;
    @FXML
    private Button bt_register_provider_view;
    @FXML
    private Button bt_back_provider_view;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_back_provider_view.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_register_provider_view.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "add-provider.fxml", "REGISTER PROVIDER");
            }
        });
        bt_search_provider_view.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_name_provider_view.getText().trim().isEmpty() ) {
                    DBUtils.checkProvider(event, "provider-menu.fxml", "PROVIDER MENU", tf_name_provider_view.getText());

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
