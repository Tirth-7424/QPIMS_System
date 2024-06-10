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
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tirth
 */
public class LoginFXMLController implements Initializable {

// Below are the FXML elements used in developing the Login page.
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
    CustomerModel model = new CustomerModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

// login function which will check the user credentials and login to the mainmenu page. 
    @FXML
    public void login() {
        String loginid = LoginIDtxt.getText(); // Storing login ID from text field to suitable variable. 
        String password = passwordtxt.getText(); // Storing paassword from text field to suitable variable. 

        boolean b = model.checkLogin(loginid, password); // Calling the checkLogin function from model which will return the boolean signifying the presence of login credentials.

        if (b) {
            try { // If boolean is true than the scene will be set to MainMenuFXML.
                App.setRoot("MainMenuFXML");
            } catch (IOException ex) { // On error
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { // if the boolean is not true, the following pop-up message will be displayed.
            displayMessage("Details not found, Please re-enter or register!");
        }

    }

    @FXML
    private void registration(ActionEvent event) throws IOException {
        // Change the scene to RegisterFXML. 
        App.setRoot("RegisterFXML");
    }

    @FXML
    public void exit() {
        // Exit the system.
        System.exit(0);
    }

    public void displayMessage(String message) {
        // Function developed for pop-up display custamized messages. 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isInteger(String a) {
        // Function to check the format of input integer. 
        try {

            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
