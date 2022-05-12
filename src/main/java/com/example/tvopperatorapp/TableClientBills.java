package com.example.tvopperatorapp;

import javafx.beans.property.*;

import java.time.LocalDate;

public class TableClientBills {
    private IntegerProperty numberProperty;
    private DoubleProperty priceProperty;
    private ObjectProperty<LocalDate> dateInvoiceProperty;
    private ObjectProperty<LocalDate> datePaymentProperty;
    private StringProperty statusProperty;

    public TableClientBills(){
        this.numberProperty = new SimpleIntegerProperty();
        this.priceProperty = new SimpleDoubleProperty();
        this.dateInvoiceProperty = new SimpleObjectProperty<>();
        this.datePaymentProperty = new SimpleObjectProperty<>();
        this.statusProperty = new SimpleStringProperty();
    }

    public String getStatus(){
        return statusProperty.get();
    }
    public void setStatus(String status) {
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

    public Object getDateInvoice(){
        return dateInvoiceProperty.get();
    }
    public void setDateInvoice(LocalDate dateInvoice){
        this.dateInvoiceProperty.set(dateInvoice);
    }
    public ObjectProperty<LocalDate> getDateInvoiceProperty() {
        return dateInvoiceProperty;
    }

    public Object getDatePayment(){
        return datePaymentProperty.get();
    }
    public void setDatePayment(LocalDate datePayment){
        this.datePaymentProperty.set(datePayment);
    }
    public ObjectProperty<LocalDate> getDatePaymentProperty() {
        return datePaymentProperty;
    }

}
