module lk.ijse.rdfcarrentals {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.management;
    requires net.sf.jasperreports.core;
    requires fontawesomefx;

    opens lk.ijse.rdfcarrentals.controller to javafx.fxml;
    exports lk.ijse.rdfcarrentals;
}