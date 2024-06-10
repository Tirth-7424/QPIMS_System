/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

/*
CustomerCreateFXMLController is associated with CustomerCreateFXML, this file provides accessibilty to create a new customer.
Several data validation steps are included. 
*/

package com.mycompany.qpims3;
// Below are the imports help in fetching library and thier elements to be used in application.

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

// below are the FXML elements used in the CustomerCreate FXML page.
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
    CustomerModel model = new CustomerModel();//intiaziling an instance of Customer model. 
    List< Customer> results; // initializing a customer list object. 
    private final ObservableList<Customer> CustomerList
            = FXCollections.observableArrayList();// initializing an observableList of customer class.

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Customer1switch(ActionEvent event) throws IOException {
        // Funtion to switch the CustomerFXML
        App.setRoot("CustomerFXML");
    }

    @FXML
    private void buttonAddCustomer(ActionEvent event) {

        if (!Fnametxt.getText().isEmpty() && !Lnametxt.getText().isEmpty() && !Addresstxt.getText().isEmpty() && !Pnumbertxt.getText().isEmpty() && isInteger(Pnumbertxt.getText()) == true) {
            // This condition will be satisfied if all the required values are provided. 
            String Fname = Fnametxt.getText(); // Storing Fname in a variable.
            String Lname = Lnametxt.getText(); // Storing Lname in a variable.
            String Address = Addresstxt.getText(); // Storing Address in a variable.
            int Phnumber = Integer.parseInt(Pnumbertxt.getText()); // parsing int and storing in a variable.

            model.addCustomer(Fname, Lname, Address, Phnumber); // adding customer
            displayMessage("Customer Successfully added!!\nCustomer ID: "+model.getLastId()); //pop-up message after customer is added successfully.

            clearfields(); // fields will be cleared for the next entery.

        } else {
            // if the fields are not provided, the following message will be prompted.
            displayMessage("Please fill-up each and every field correctly!");
            Pnumbertxt.clear();
        }

    }

    public void displayMessage(String message) {

        // Function to create pop-up message.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isInteger(String a) {
        // Function to validate if the data provided is an integer. 
        try {

            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void clearfields() {
        // Function to clear all the fields.
        Fnametxt.clear();
        Lnametxt.clear();
        Addresstxt.clear();
        Pnumbertxt.clear();
        CustomerList.clear();

    }

}
