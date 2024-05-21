package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;

public class ManagerReportController {

    @FXML
    private void goBack() throws IOException {
        App.setRoot("mainMenu");
    }
    
    
    @FXML
    private void exit() throws IOException {
        System.exit(0);
    }
}
