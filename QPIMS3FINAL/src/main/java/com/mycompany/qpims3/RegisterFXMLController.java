/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
/*
RegiterFXMLController is associated with RegisterFXML page. This file provides method to read the text field and create
a user after validating the information provided. 
*/
package com.mycompany.qpims3;
// Below are the imports help in fetching library and thier elements to be used in application.

import com.mycompany.qpims3.CustomerModel;
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
public class RegisterFXMLController implements Initializable {

// Below are the FXML elements used in the development of Register page.
    @FXML
    private Button registerbtn;
    @FXML
    private TextField LoginIDtxt;
    @FXML
    private TextField passwordtxt;
    @FXML
    private Button cancelbtn;
    @FXML
    private TextField Lnametxt;
    @FXML
    private TextField Fnametxt;
    @FXML
    private TextField emailtxt;
    /**
     * Initializes the controller class.
     */

    CustomerModel model = new CustomerModel();// initialization of Customer model.

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void adduser(ActionEvent event) {
        // Function to add new user to the database.

        // Below are the data validation steps.
        if (LoginIDtxt.getText().isEmpty() || passwordtxt.getText().isEmpty() || emailtxt.getText().isEmpty() || Fnametxt.getText().isEmpty() || Lnametxt.getText().isEmpty()) {
            displayMessage("Please check each and every field!!");
            clearfields();
        } else {
            // If the data is valid following lines of code will be executed.
            String LoginID = LoginIDtxt.getText();
            String Password = passwordtxt.getText();
            String email = emailtxt.getText();
            String Fname = Fnametxt.getText();
            String Lname = Lnametxt.getText();
            // Executing query after setting parameters.
            int r = model.addUsers(LoginID, Password, email, Fname, Lname);
            if (r > 0) {
                // If the request is proccessed, the following pop-up will displayed.
                displayMessage("Registration successful");

                clearfields();
            } else {
                // if the request is denied, the following pop-up will be displayed.
                displayMessage("Registration failed- enter both user name and valid password");
            }

        }

    }

    @FXML
    public void cancel() {
        // Changing the scene back to LoginFXML.
        try {
            App.setRoot("LoginFXML");
        } catch (IOException ex) {
            Logger.getLogger(RegisterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayMessage(String message) {
        // Function for customized pop-up message.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clearfields() {
        // Function to clear text fields. 
        LoginIDtxt.clear();
        passwordtxt.clear();
        Fnametxt.clear();
        emailtxt.clear();
        Lnametxt.clear();
    }

    public static boolean isInteger(String a) {
        // Function to validate the integer input.
        try {

            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
