package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginOrRegisterController {

    @FXML
    private void switchToLoginScreen() throws IOException {
        App.setRoot("loginScreen");
    }
    
    @FXML
    private void switchToRegisterScreen() throws IOException {
        App.setRoot("registerScreen");
    }
    
    
    @FXML
    private void exit() throws IOException {
        System.exit(0);
    }
}
