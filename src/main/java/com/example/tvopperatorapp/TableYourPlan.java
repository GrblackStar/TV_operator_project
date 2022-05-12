package com.example.tvopperatorapp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableYourPlan {
    private StringProperty nameProperty;
    private DoubleProperty priceProperty;
    private StringProperty channelsProperty;

    public TableYourPlan(){
        this.nameProperty = new SimpleStringProperty();
        this.priceProperty = new SimpleDoubleProperty();
        this.channelsProperty = new SimpleStringProperty();
    }

    // getters and setters for nameProperty
    public String getPlanName(){
        return nameProperty.get();
    }
    public void setPlanName(String planName){
        this.nameProperty.set(planName);
    }
    public StringProperty getPlanNameProperty(){
        return nameProperty;
    }

    // getters and setters for priceProperty
    public double getPrice(){
        return priceProperty.get();
    }
    public void setPriceProperty(double price){
        this.priceProperty.set(price);
    }
    public DoubleProperty getPriceProperty(){
        return priceProperty;
    }

    // getters and setters for channelsProperty
    public String getChannelName(){
        return channelsProperty.get();
    }
    public void setChannelsName(String channel){
        this.nameProperty.set(channel);
    }
    public StringProperty getChannelsProperty(){
        return channelsProperty;
    }


}
