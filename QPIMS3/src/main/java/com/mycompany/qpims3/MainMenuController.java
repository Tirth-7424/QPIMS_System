/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;
// Below are the imports help in fetching library and thier elements to be used in application.

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
public class MainMenuController implements Initializable {

// Below are the elements used in developing the FXML page. 
    @FXML
    private Button primaryButton21;
    @FXML
    private Button primaryButton211;
    @FXML
    private Button primaryButton212;
    @FXML
    private Button primaryButton2121;
    @FXML
    private Button primaryButton21211;
    @FXML
    private Button primaryButton212111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void switchToCustomerSearch(ActionEvent event) {
        // Function to switch the scene to CustomerFXML.
        try {
            App.setRoot("CustomerFXML");
        } catch (IOException ex) {
            // Catch statement for error catching.
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToPropertySearch(ActionEvent event) {
        // Function to switch the scene to SearchProperty.
        try {
            App.setRoot("SearchProperty");
        } catch (IOException ex) {
            // Catch statement for error catching.
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToJobSearch(ActionEvent event) {
        // Function to switch the scene to JobSearch.
        try {
            App.setRoot("JobSearch");
        } catch (IOException ex) {
            // Catch statement for error catching.
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToManagerReport(ActionEvent event) {
        // Function to switch the scene t Management Report.
        try {
            App.setRoot("CustomerFXML");
        } catch (IOException ex) {
            // Catch statement for error catching.
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        // Function to exit the current FXML and switch back to LoginFXML.
        try {
            App.setRoot("LoginFXML");
        } catch (IOException ex) {
            // Catch statement for error catching.
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
