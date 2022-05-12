package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPaymentController implements Initializable {
    @FXML
    private ChoiceBox<String> cb_date_add_payment;
    @FXML
    private Button bt_confirm_add_payment;
    @FXML
    private Button bt_exit_add_payment;
    @FXML
    private Label lb_egn;


    public void setUserEgn(String egn){
        lb_egn.setText(egn);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_exit_add_payment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAdminClient(event, "admin-client-menu.fxml", "CLIENT MENU", lb_egn.getText());
            }
        });
        cb_date_add_payment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cb_date_add_payment.getItems().addAll(DBUtils.choosePaymentAdmin(lb_egn.getText()));
            }
        });
        bt_confirm_add_payment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cb_date_add_payment.getValue()!=null){

                    DBUtils.makePaymentAdmin(event, lb_egn.getText(), cb_date_add_payment.getValue());
                    DBUtils.changeSceneAdminClient(event, "client-bills-admin.fxml", "CLIENT BILLS", lb_egn.getText());

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
