module com.mycompany.guiprototype {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.guiprototype to javafx.fxml;
    exports com.mycompany.guiprototype;
}
