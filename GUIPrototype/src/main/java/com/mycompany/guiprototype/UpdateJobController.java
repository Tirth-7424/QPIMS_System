package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.fxml.FXML;

public class UpdateJobController {

    @FXML
    private void goBack() throws IOException {
        App.setRoot("jobSearch");
    }
}