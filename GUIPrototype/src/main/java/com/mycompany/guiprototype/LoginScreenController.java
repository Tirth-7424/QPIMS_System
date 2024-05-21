package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginScreenController {

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
    
    @FXML
    private void goBack() throws IOException {
        App.setRoot("loginOrRegister");
    }
    
}
