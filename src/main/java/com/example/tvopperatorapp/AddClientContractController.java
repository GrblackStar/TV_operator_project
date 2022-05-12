package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientContractController implements Initializable {

    @FXML
    private Button bt_cancel_client_contract;
    @FXML
    private Button bt_ok_client_contract;
    @FXML
    private TextField tf_first_name_client_contract;
    @FXML
    private TextField tf_last_name_client_contract;
    @FXML
    private TextField tf_egn_client_contract;
    @FXML
    private TextField tf_address_client_contract;
    @FXML
    private TextField tf_phone_client_contract;
    @FXML
    private ChoiceBox<String> cb_package_client_contract;
    @FXML
    private RadioButton rb_1y_client_contract;
    @FXML
    private RadioButton rb_2y_client_contract;
    @FXML
    private RadioButton rb_4y_client_contract;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_1y_client_contract.setToggleGroup(toggleGroup);
        rb_2y_client_contract.setToggleGroup(toggleGroup);
        rb_4y_client_contract.setToggleGroup(toggleGroup);
        rb_1y_client_contract.setSelected(true);

        cb_package_client_contract.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cb_package_client_contract.getItems().addAll(DBUtils.chooseClientPlan());
            }
        });
        bt_cancel_client_contract.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_ok_client_contract.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleYears = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                if(!tf_first_name_client_contract.getText().trim().isEmpty() &&
                        !tf_last_name_client_contract.getText().trim().isEmpty() &&
                        !tf_egn_client_contract.getText().trim().isEmpty() &&
                        !tf_address_client_contract.getText().trim().isEmpty() &&
                        !tf_phone_client_contract.getText().trim().isEmpty() &&
                        (cb_package_client_contract.getValue()!=null)){
                    DBUtils.registerClientAdmin(event, tf_first_name_client_contract.getText(),
                            tf_last_name_client_contract.getText(), tf_egn_client_contract.getText(),
                            tf_address_client_contract.getText(), tf_phone_client_contract.getText(),
                            cb_package_client_contract.getValue(), toggleYears);
                    DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");

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
