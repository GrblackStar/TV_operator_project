package com.example.tvopperatorapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeProviderInfoController implements Initializable {

    @FXML
    private Label lb_name;

    public void setProviderName(String name){
        lb_name.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
