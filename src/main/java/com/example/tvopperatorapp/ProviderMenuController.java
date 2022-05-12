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

public class ProviderMenuController implements Initializable {
    @FXML
    private Button bt_exit_provider_menu;
    @FXML
    private Button bt_update_provider_menu;
    @FXML
    private Button bt_delete_provider_menu;
    @FXML
    private Label lb_name_provider_menu;
    @FXML
    private TableView<TableProviderMenu> tv_table_provider_menu;
    @FXML
    private TableColumn<TableProviderMenu, String> col_channel_provider_menu;
    @FXML
    private TableColumn<TableProviderMenu, String> col_category_provider_menu;


    public void setProviderName(String name){
        lb_name_provider_menu.setText(name);
    }

    private void populateTable(ObservableList<TableProviderMenu> list){
        tv_table_provider_menu.setItems(list);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_channel_provider_menu.setCellValueFactory(cellData -> cellData.getValue().getChannelNameProperty());
        col_category_provider_menu.setCellValueFactory(cellData -> cellData.getValue().getChannelCategoryProperty());

        try {
            ObservableList<TableProviderMenu> list = DBUtils.getAllTableProviderMenu(lb_name_provider_menu.getText());
            populateTable(list);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        bt_exit_provider_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneToMain(event, "admin-menu.fxml", "ADMIN MENU");
            }
        });
        bt_delete_provider_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneProvider(event, "delete-question-provider.fxml", "DELETE PROVIDER", lb_name_provider_menu.getText());
            }
        });
        bt_update_provider_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneProvider(event,"change-provider-info.fxml", "UPDATE PRoVIDER", lb_name_provider_menu.getText());
            }
        });

    }
}
