package com.example.tvopperatorapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableProviderMenu {
    private StringProperty channelNameProperty;
    private StringProperty channelCategoryProperty;

    public TableProviderMenu(){
        this.channelNameProperty = new SimpleStringProperty();
        this.channelCategoryProperty = new SimpleStringProperty();
    }


    public String getChannelName() {
        return channelNameProperty.get();
    }
    public void setChannelName(String name){
        this.channelNameProperty.set(name);
    }
    public StringProperty getChannelNameProperty(){
        return channelNameProperty;
    }

    public String getChannelCategory(){
        return channelCategoryProperty.get();
    }
    public void setChannelCategory(String category){
        this.channelCategoryProperty.set(category);
    }
    public StringProperty getChannelCategoryProperty(){
        return channelCategoryProperty;
    }



}
