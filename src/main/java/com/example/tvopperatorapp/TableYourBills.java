package com.example.tvopperatorapp;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

public class TableYourBills {
    private IntegerProperty numberProperty;
    private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
    private DoubleProperty priceProperty;
    private StringProperty statusProperty;

    public TableYourBills(){
        this.numberProperty = new SimpleIntegerProperty();
        this.priceProperty = new SimpleDoubleProperty();
        this.dateProperty = new SimpleObjectProperty<>();
        this.statusProperty = new SimpleStringProperty();
    }


    public String getStatus(){
        return statusProperty.get();
    }
    public void setStatus(String status){
        this.statusProperty.set(status);
    }
    public StringProperty getStatusProperty(){
        return statusProperty;
    }

    public double getPrice(){
        return priceProperty.get();
    }
    public void setPrice(double price){
        this.priceProperty.set(price);
    }
    public DoubleProperty getPriceProperty(){
        return priceProperty;
    }

    public int getNumber(){
        return numberProperty.get();
    }
    public void setNumber(int number){
        this.numberProperty.set(number);
    }
    public IntegerProperty getNumberProperty(){
        return numberProperty;
    }

    public Object getDate() {
        return dateProperty.get();
    }
    public void setDate(LocalDate dateProperty) {
        this.dateProperty.set(dateProperty);
    }
    public ObjectProperty<LocalDate> getDateProperty() {
        return dateProperty;
    }


}
