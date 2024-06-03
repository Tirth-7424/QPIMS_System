module com.mycompany.qpims3 {
   requires javafx.controls;
   requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.desktop;

    opens com.mycompany.qpims3 to javafx.fxml;
    exports com.mycompany.qpims3;
}
