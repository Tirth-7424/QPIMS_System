package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;

public class CreateCustomerController {

    @FXML
    private void goBack() throws IOException {
        App.setRoot("customerSearch");
    }
}