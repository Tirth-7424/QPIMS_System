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
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author tirth
 */
public class LoginFXMLController implements Initializable {


    @FXML
    private Button loginbtn;
    @FXML
    private TextField LoginIDtxt;
    @FXML
    private TextField passwordtxt;
    @FXML
    private Button registerbtn;
    @FXML
    private Button exitbtn;
    /**
     * Initializes the controller class.
     */
    CustomerModel model=new CustomerModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    

    @FXML
    public void login(){
    String loginid=LoginIDtxt.getText();
    String password=passwordtxt.getText();

        boolean b=model.checkLogin(loginid, password);
    
    if(b){
        try {
            App.setRoot("MainMenuFXML");
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    else{
    displayMessage("Details not found, Please re-enter or register!");
    }
    
    }
    @FXML
    private void registration(ActionEvent event) throws IOException {
        App.setRoot("RegisterFXML");     
    }    
    
    @FXML
    public void exit(){
    System.exit(0);
    }
    
         public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static boolean isInteger(String a) {
        try {
           
            Integer.parseInt(a);
           return true;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
}
