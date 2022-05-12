package com.example.tvopperatorapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChangeClientInfoController {
    @FXML
    private Label lb_egn;


    public void setUserEgn(String egn){
        lb_egn.setText(egn);
    }

}
