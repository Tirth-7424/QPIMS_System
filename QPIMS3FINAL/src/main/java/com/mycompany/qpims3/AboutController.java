/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


/* 
About Controller is associated with About FXML page, basicallt, the About FXML page depicts the main functionalities
of the App and information on the different sections of app. About controller is only one button that is back which
will lead the user to mainmenu when pressed.
*/
package com.mycompany.qpims3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author tirth
 */
public class AboutController implements Initializable {



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    

    @FXML
    private void goBack() throws IOException{
        App.setRoot("MainMenuFXML");
    }

}
