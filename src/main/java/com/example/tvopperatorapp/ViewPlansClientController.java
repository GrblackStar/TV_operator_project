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
import java.util.ResourceBundle;

public class ViewPlansClientController implements Initializable {
    @FXML
    private Button bt_back_plans_client;
    @FXML
    private Button bt_logout_plans_client;
    @FXML
    private TableView<TableYourPlan> tv_your_plans_client;
    @FXML
    private TableView<TableAllPlans> tv_all_plans_client;
    @FXML
    private TableColumn<TableYourPlan, String > col_your_name_plans_client;
    @FXML
    private TableColumn<TableYourPlan, Double > col_your_price_plans_client;
    @FXML
    private TableColumn<TableYourPlan, String > col_your_channels_plans_client;
    @FXML
    private TableColumn<TableAllPlans, String > col_all_name_plans_client;
    @FXML
    private TableColumn<TableAllPlans, Double > col_all_price_plans_client;
    @FXML
    private TableColumn<TableAllPlans, String > col_all_channels_plans_client;
    @FXML
    private Label lb_username;



    public void setUserUsername(String username){
        lb_username.setText(username);
    }



    private void populateYourTable(ObservableList<TableYourPlan> yourPlans){
        tv_your_plans_client.setItems(yourPlans);
    }

    private void populateAllTable(ObservableList<TableAllPlans> allPlans){
        tv_all_plans_client.setItems(allPlans);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_your_name_plans_client.setCellValueFactory(cellData -> cellData.getValue().getPlanNameProperty());
        col_your_price_plans_client.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        col_your_channels_plans_client.setCellValueFactory(cellData -> cellData.getValue().getChannelsProperty());

        col_all_name_plans_client.setCellValueFactory(cellData -> cellData.getValue().getPlanNamePropertyAll());
        col_all_price_plans_client.setCellValueFactory(cellData -> cellData.getValue().getPricePropertyAll().asObject());
        col_all_channels_plans_client.setCellValueFactory(cellData -> cellData.getValue().getChannelsPropertyAll());
        try {
            ObservableList<TableYourPlan> yourPlans = DBUtils.getAllTableYourPlan(lb_username.getText());
            populateYourTable(yourPlans);

            ObservableList<TableAllPlans> allPlans = DBUtils.getAllTableAllPlans();
            populateAllTable(allPlans);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        bt_back_plans_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneClient(event, "client-menu.fxml", "WELCOME", lb_username.getText());
            }
        });
        bt_logout_plans_client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "sample.fxml", "WELCOME");
            }
        });

    }
}
