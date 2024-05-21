package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;

public class UpdateCustomerController {

    @FXML
    private void goBack() throws IOException {
        App.setRoot("customerSearch");
    }
}