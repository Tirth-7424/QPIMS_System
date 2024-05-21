package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    private void switchToCustomerSearch() throws IOException {
        App.setRoot("customerSearch");
    }
    
    @FXML
    private void switchToPropertySearch() throws IOException {
        App.setRoot("propertySearch");
    }
    
    @FXML
    private void switchToJobSearch() throws IOException {
        App.setRoot("jobSearch");
    }
    
    @FXML
    private void switchToManagerReport() throws IOException {
        App.setRoot("managerReport");
    }
    
    @FXML
    private void exit() throws IOException {
        App.setRoot("loginOrRegister");
    }
}
