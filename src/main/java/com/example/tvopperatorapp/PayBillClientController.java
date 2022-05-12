package com.example.tvopperatorapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PayBillClientController implements Initializable {
    @FXML
    private Button bt_back_pay_bill_client;
    @FXML
    private Button bt_logout_pay_bill_client;
    @FXML
    private Button bt_pay_pay_bill_client;
    @FXML
    private ChoiceBox<String> cb_bill_pay_bill_client;
    @FXML
    private TextField tf_card_pay_bill_client;
    @FXML
    private Label lb_username;



    public void setUserUsername(String username){
        lb_username.setText(username);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_bill_pay_bill_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cb_bill_pay_bill_client.getItems().addAll(DBUtils.choosePaymentClient(lb_username.getText()));
            }
        });
        bt_back_pay_bill_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneClient(event, "client-menu.fxml", "MENU", lb_username.getText());
            }
        });
        bt_logout_pay_bill_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "WELCOME");
            }
        });
        bt_pay_pay_bill_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_card_pay_bill_client.getText().trim().isEmpty() && (cb_bill_pay_bill_client.getValue()!=null) && tf_card_pay_bill_client.getLength()==5){
                    DBUtils.makePayment(event, lb_username.getText(), cb_bill_pay_bill_client.getValue());
                    DBUtils.changeSceneClient(event, "client-menu.fxml", "MENU", lb_username.getText());

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
