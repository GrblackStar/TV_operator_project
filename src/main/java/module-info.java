module com.example.tvopperatorapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tvopperatorapp to javafx.fxml;
    exports com.example.tvopperatorapp;
}