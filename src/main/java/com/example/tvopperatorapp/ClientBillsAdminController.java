package com.example.tvopperatorapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClientBillsAdminController implements Initializable {
    @FXML
    private Button bt_pay;
    @FXML
    private Button bt_back;
    @FXML
    private TableView<TableClientBills> tv_bills;
    @FXML
    private TableColumn<TableClientBills, Integer> col_number;
    @FXML
    private TableColumn<TableClientBills, Double> col_price;
    @FXML
    private TableColumn<TableClientBills, LocalDate> col_invoice;
    @FXML
    private TableColumn<TableClientBills, LocalDate> col_payment;
    @FXML
    private TableColumn<TableClientBills, String> col_status;
    @FXML
    private Label lb_egn;


    public void setUserEgn(String egn){
        lb_egn.setText(egn);
    }

    private void populateTable(ObservableList<TableClientBills> list){
        tv_bills.setItems(list);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_number.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty().asObject());
        col_price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        col_invoice.setCellValueFactory(cellData -> cellData.getValue().getDateInvoiceProperty());
        col_payment.setCellValueFactory(cellData -> cellData.getValue().getDatePaymentProperty());
        col_status.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());

        try {
            ObservableList<TableClientBills> clientBills = DBUtils.getAllTableClientBills(lb_egn.getText());
            populateTable(clientBills);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        bt_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAdminClient(event, "admin-client-menu.fxml", "CLIENT MENU", lb_egn.getText());
            }
        });
        bt_pay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneAdminClient(event, "add-payment.fxml", "PAY BILLS CLIENT", lb_egn.getText());
            }
        });

    }
}
