/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
public class MainMenuController implements Initializable {

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
        try {
            App.setRoot("CustomerFXML");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToPropertySearch(ActionEvent event) {
        try {
            App.setRoot("SearchProperty");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToJobSearch(ActionEvent event) {
        try {
            App.setRoot("JobSearch");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToManagerReport(ActionEvent event) {
        try {
            App.setRoot("CustomerFXML");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        try {
            App.setRoot("LoginFXML");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
