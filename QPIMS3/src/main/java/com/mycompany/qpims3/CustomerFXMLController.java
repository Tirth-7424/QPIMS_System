/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author tirth
 */
public class CustomerFXMLController implements Initializable {
// below are the FXML elements used in the Customer FXML page.

    @FXML
    private Button Searchbtn;
    @FXML
    private Button SearchLastNameID;
    @FXML
    private Button SearchIDbtn;
    @FXML
    private Button addcustomerbtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button backbtn;
    @FXML
    private TextField Lnametxt;
    @FXML
    private TextField Pnumbertxt;
    @FXML
    private TextField CIDtxt;
    @FXML
    private AnchorPane customerSearchPane;
    @FXML
    private AnchorPane updateCustomerPane;
    @FXML
    private Button Cancelbutton;
    @FXML
    private TextField Fnameupdatetxt;
    @FXML
    private TextField Lnameupdatetxt;
    @FXML
    private TextField Pnumberupdatetxt;
    @FXML
    private TextField Addressupdatetxt;
    @FXML
    private Button UpdateCustomer;
    @FXML
    private ListView<Customer> listview;

    /**
     * Initializes the controller class.
     */
    CustomerModel model = new CustomerModel();//initilaize- important
    List< Customer> results; //initilaizing a customer list named result
    int numberOfEntries; // initializing a int variable named numberOfEnteries
    private final ObservableList<Customer> CustomerList
            = //initializing a ObservableList of Customer class named CustomerList.
            FXCollections.observableArrayList();
    Customer updateCustomer; //initiaziling a updateCustomer object.
    private Customer customer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // As we are using two panes in one view, this initilization will set one pane active and another deactive.

        customerSearchPane.setVisible(true);//customerSearchPane is set to visible when running the application.
        updateCustomerPane.setVisible(false);//updateCustomerPane will be only visible when it will be neccesary. 

    }

    @FXML
    private void Customer2switch(ActionEvent event) throws IOException {
        // This function will switch the scene to CustomerCreateFXML.
        updatebtn.setDisable(true);
        App.setRoot("CustomerCreateFXML");
    }

    @FXML
    private void buttonFindPhoneHandler(ActionEvent event) { // Function to find the customer with the help of Phone number.

        Lnametxt.clear();
        CIDtxt.clear();
        updatebtn.setDisable(true); //Setting update button to disable, the update button should only be active when there is any entry selected.
        String a = Pnumbertxt.getText(); //Storing the phone number from text field to a string.
        if (a.isEmpty() || a.equals("")) {
            displayMessage("Please Enter the Phone Number!!"); // If the text field is empty, a appropriate message will be delivered.
        } else {
            if (isInteger(Pnumbertxt.getText()) == true) {
                // if the phone number is integer then the function from model will be called and result will be returned. 
                int foundPnumber = Integer.parseInt(Pnumbertxt.getText());
                results = model.getCustomersByPhone(foundPnumber);

                numberOfEntries = results.size();
                if (numberOfEntries != 0) {
                    // If the result contain entries then following lines of code will be executed.
                    listview.setItems(CustomerList); // bind to contactsList  
                    listview.setCellFactory(param -> new CustomerListID());
                    getCustomersbyPhone(foundPnumber); //This will set the values to the listView.   
                    listview.getSelectionModel().selectedItemProperty().addListener(
                            (var observableValue, var oldValue, var newValue) -> {
                                if (newValue != null) {
                                    // This condition will help to select the customer details and when selected, the update button will be acitve.
                                    updateCustomer = newValue; // the newValue i.e. the selected customer object will be stored in the a variable initialized before. 
                                    setfields(updateCustomer); // This will help in set-up all the values in update pane.
                                    updatebtn.setDisable(false);

                                }
                            }
                    );
                } else {
                    // If there is no entry found, a message will be displayed of 'Not Found'.
                    displayMessage("Not found");
                    Pnumbertxt.clear();
                    CustomerList.clear();
                }
            } else {
                // If the provided input is not integer the following message will be pop-up.
                displayMessage("Only Integer values accepted for phone number.");
                Pnumbertxt.clear();
                CustomerList.clear();
            }
        }

    }

    @FXML
    private void buttonFindIDHandler(ActionEvent event) { // This function is used to find the required customer with the help of Customer Id. 
        Lnametxt.clear();
        Pnumbertxt.clear();
        updatebtn.setDisable(true);

        String foundID = CIDtxt.getText(); //Storing Customer Id in a appropriate variable. 

        if (foundID.isEmpty()) {
            displayMessage("Please Enter a ID!!"); // If the variable is empty, then an appropriate message will be pop-up.
        } else {

            if (isInteger(foundID)) {
                // If the ID is valid integer, the following lines of codewill be executed.
                int id = Integer.parseInt(foundID);
                results = model.getCustomersByID(id); // The integer will be passed as an argument to function situated in model for fetching the data from SQL Databse.

                numberOfEntries = results.size(); // This will determine the number of records present in the result.
                if (numberOfEntries != 0) {
                    // If the number of records are not zero then following lines of code will be executed.
                    listview.setItems(CustomerList); // bind to contactsList
                    listview.setCellFactory(param -> new CustomerListID());
                    getCustomersbyID(id); // This will help set-up the entries on the list view object.  
                    listview.getSelectionModel().selectedItemProperty().addListener(
                            (var observableValue, var oldValue, var newValue) -> {
                                if (newValue != null) {
                                    // This condition will help to select the customer details and when selected, the update button will be acitve.
                                    updateCustomer = newValue; // the newValue i.e. the selected customer object will be stored in the a variable initialized before. 
                                    setfields(updateCustomer); // This will help in set-up all the values in update pane.
                                    updatebtn.setDisable(false);

                                }
                            }
                    );
                } else {
                    // If the customer Id is not found
                    displayMessage("Not found");
                    CIDtxt.clear();
                    clearfields();

                }
            } else {
                // If the ID provided is not integer.
                displayMessage("ID should be Integer.");
            }
        }
    }

    @FXML
    private void buttonFindNameHandler(ActionEvent event) {
        CIDtxt.clear();
        Pnumbertxt.clear();
        updatebtn.setDisable(true);
        String foundName = Lnametxt.getText();

        if (foundName.isEmpty()) {
            displayMessage("Please Enter a Last Name!!");
        } else {

            results = model.getCustomersByName(foundName);

            numberOfEntries = results.size();
            if (numberOfEntries != 0) {
                listview.setItems(CustomerList); // bind to contactsList
                listview.setCellFactory(param -> new CustomerListID());
                getCustomersbyName(foundName);
                listview.getSelectionModel().selectedItemProperty().addListener(
                        (var observableValue, var oldValue, var newValue) -> {
                            if (newValue != null) {

                                updateCustomer = newValue;
                                setfields(updateCustomer);
                                updatebtn.setDisable(false);

                            }
                        }
                );

            } else {
                displayMessage("Not found");
                clearfields();
            }
        }

    }

    public void displayMessage(String message) {
        // Function to display pop-up messages. 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isInteger(String a) {
        // Function to validate the provided input is in interger format. 
        try {

            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void updateCustomerSwitch(ActionEvent event) throws IOException {
        // Fucntion to switch the panes. 
        customerSearchPane.setVisible(false);
        updateCustomerPane.setVisible(true);
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
        // Function to update Customer. 
        try {

            int CID = updateCustomer.getCustomerID(); // Storing Customer ID in a appropriate variable. 
            String Fname = Fnameupdatetxt.getText();// Storing Fname in a appropriate variable. 
            String Lname = Lnameupdatetxt.getText();// Storing Lname ID in a appropriate variable. 
            String Address = Addressupdatetxt.getText();// Storing Address in a appropriate variable. 
            int Phnumber = Integer.parseInt(Pnumberupdatetxt.getText()); // Storing Phnumber in a appropriate variable. 

            model.updateCustomer(CID, Fname, Lname, Address, Phnumber); //Updating customer, this will call another function from model and that function generate the SQL query for updating the customer.
            displayMessage("Customer Update successful!!"); // Pop-up Message after updating customer.
            clearfieldsupdate(); //Fields will be cleared.
        } catch (NumberFormatException e) {
            // Handle the case where parsing fails (e.g., invalid integer input)
            displayMessage("Invalid input for CID or Phone Number. Please enter valid numbers.");
        } catch (Exception e) {
            // Handle other exceptions (e.g., database errors)
            displayMessage("Error updating customer: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }

    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        // Function to go back in MainMenu.
        App.setRoot("MainMenuFXML");
    }

    public void clearfields() {
        // Function clear fields. 
        Lnametxt.clear();
        Pnumbertxt.clear();
        CustomerList.clear();

    }

    public void clearfieldsupdate() {
        // Function to clear field in update pane. 
        Fnameupdatetxt.clear();
        Lnameupdatetxt.clear();
        Pnumberupdatetxt.clear();
        Addressupdatetxt.clear();

    }

    public void getCustomersbyPhone(int a) {
        //getting customer by Phone number. 
        CustomerList.setAll(model.getCustomersByPhone(a));

    }

    public void getCustomersbyID(int a) {
        //getting customer by customer ID.
        CustomerList.setAll(model.getCustomersByID(a));

    }

    public void getCustomersbyName(String a) {
        // getting customer by Customer name. This funtion will check the upper and lower case both.
        CustomerList.setAll(model.getCustomersByName(a));
        if (CustomerList.isEmpty()) {
            CustomerList.setAll(model.getCustomersByName(a.toLowerCase()));
        }
        if (CustomerList.isEmpty()) {
            CustomerList.setAll(model.getCustomersByName(a.toUpperCase()));
        }

    }

    public void setfields(Customer newValue) {

        // This function is to pre-set the fields in the update pane.
        Fnameupdatetxt.setText(newValue.getFname());
        Addressupdatetxt.setText(newValue.getAddress());
        Lnameupdatetxt.setText(newValue.getLname());
        Pnumberupdatetxt.setText("" + newValue.getPhoneNumber());
        System.out.println(newValue.getCustomerID() + "----");
        System.out.println(newValue.getLname() + "----");
        System.out.println(newValue.getAddress() + "----");
    }

    private static class CustomerListID extends ListCell<Customer> {
        // To-string method for List elements to be displayed on the list view object.

        @Override
        protected void updateItem(Customer customer, boolean empty) {
            super.updateItem(customer, empty);
            if (empty || customer == null) {
                setText(null);
            } else {
                setText(customer.getCustomerID() + " " + customer.getLname() + " " + customer.getFname());
            }
        }
    }

}
