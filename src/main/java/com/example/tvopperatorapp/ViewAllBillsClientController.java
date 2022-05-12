package com.example.tvopperatorapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewAllBillsClientController implements Initializable{
    @FXML
    private Button bt_back_view_all_bills_client;
    @FXML
    private Button bt_logout_view_all_bills_client;
    @FXML
    private TableView<TableYourBills> tv_table_view_all_bills_client;
    @FXML
    private TableColumn<TableYourBills, Integer> col_number_view_all_bills_client;
    @FXML
    private TableColumn<TableYourBills, LocalDate> col_date_view_all_bills_client;
    @FXML
    private TableColumn<TableYourBills, Double> col_price_view_all_bills_client;
    @FXML
    private TableColumn<TableYourBills, String> col_status_view_all_bills_client;
    @FXML
    private Label lb_username;




    public void setUserUsername(String username){
        lb_username.setText(username);
    }

    private void populateBillTable(ObservableList<TableYourBills> yourBills){
        tv_table_view_all_bills_client.setItems(yourBills);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_number_view_all_bills_client.setCellValueFactory(cellDate -> cellDate.getValue().getNumberProperty().asObject());
        col_date_view_all_bills_client.setCellValueFactory(cellDate -> cellDate.getValue().getDateProperty());
        col_price_view_all_bills_client.setCellValueFactory(cellDate -> cellDate.getValue().getPriceProperty().asObject());
        col_status_view_all_bills_client.setCellValueFactory(cellDate -> cellDate.getValue().getStatusProperty());

        try {
            ObservableList<TableYourBills> yourBills = DBUtils.getAllTableYourBills(lb_username.getText());
            populateBillTable(yourBills);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        bt_back_view_all_bills_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneClient(event, "client-menu.fxml", "WELCOME", lb_username.getText());
            }
        });
        bt_logout_view_all_bills_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "WELCOME");
            }
        });
    }
}
