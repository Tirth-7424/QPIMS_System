/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class CustomerCreateFXMLController implements Initializable {


    @FXML
    private Button secondaryButton;
    @FXML
    private TextField Fnametxt;
    @FXML
    private TextField Lnametxt;
    @FXML
    private TextField Pnumbertxt;
    @FXML
    private TextField Addresstxt;
    @FXML
    private Button AddCustomer;
    
    /**
     * Initializes the controller class.
     */
    
    CustomerModel model=new CustomerModel();//initilaize- important
    List< Customer > results;
    private final ObservableList<Customer> CustomerList =
	      FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void Customer1switch(ActionEvent event) throws IOException {
       App.setRoot("CustomerFXML");
}
    
    
    @FXML
    private void buttonAddCustomer(ActionEvent event) {
        
      
        if(!Fnametxt.getText().isEmpty() && !Lnametxt.getText().isEmpty() && !Addresstxt.getText().isEmpty() && !Pnumbertxt.getText().isEmpty() && isInteger(Pnumbertxt.getText()) == true){
        String Fname = Fnametxt.getText();
        String Lname = Lnametxt.getText();
        String Address = Addresstxt.getText();
        int Phnumber = Integer.parseInt(Pnumbertxt.getText());

        model.addCustomer(Fname, Lname, Address, Phnumber);
        displayMessage("Customer Successfully added!!");
        
        clearfields();
        
        }
        else{
        displayMessage("Please fill-up each and every field correctly!");
        Pnumbertxt.clear();
        }
       
        
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
         
    
      
    public void clearfields(){
        Fnametxt.clear();
        Lnametxt.clear();
        Addresstxt.clear();
        Pnumbertxt.clear();
        CustomerList.clear();
   
   }
    
}
