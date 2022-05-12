package com.example.tvopperatorapp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableAllPlans {
    private StringProperty nameAllProperty;
    private DoubleProperty priceAllProperty;
    private StringProperty channelsAllProperty;

    public TableAllPlans(){
        this.nameAllProperty = new SimpleStringProperty();
        this.priceAllProperty = new SimpleDoubleProperty();
        this.channelsAllProperty = new SimpleStringProperty();
    }

    // getters and setters for nameProperty
    public String getPlanNameAll(){
        return nameAllProperty.get();
    }
    public void setPlanNameAll(String planNameAll){
        this.nameAllProperty.set(planNameAll);
    }
    public StringProperty getPlanNamePropertyAll(){
        return nameAllProperty;
    }

    // getters and setters for priceProperty
    public double getPriceAll(){
        return priceAllProperty.get();
    }
    public void setPricePropertyAll(double price){
        this.priceAllProperty.set(price);
    }
    public DoubleProperty getPricePropertyAll(){
        return priceAllProperty;
    }

    // getters and setters for channelsProperty
    public String getChannelNameAll(){
        return channelsAllProperty.get();
    }
    public void setChannelsNameAll(String channel){
        this.channelsAllProperty.set(channel);
    }
    public StringProperty getChannelsPropertyAll(){
        return channelsAllProperty;
    }
}
