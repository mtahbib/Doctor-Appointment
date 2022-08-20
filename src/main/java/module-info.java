module com.da {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.da to javafx.fxml;
    exports com.da;
    exports com.da.controller;
    exports com.da.model;
    opens com.da.controller to javafx.fxml;
}