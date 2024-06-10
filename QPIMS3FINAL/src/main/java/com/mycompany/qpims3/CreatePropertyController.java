/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

/*
CreatePropertyController is associated with CreateProperty FXML page, basicallly, using this contoller page,
the values in the test fields provided in FXML elements are utilized and if valid a new property is created. 
Several data checks also exists within this file which are nesseccary for error prevention.
*/

package com.mycompany.qpims3;
// Below are the imports help in fetching library and thier elements to be used in application.

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreatePropertyController {
// below are the FXML elements used in the Create property page.

    @FXML
    private ChoiceBox propertyTypeChoiceBox;
    @FXML
    private ChoiceBox stateChoiceBox;

    @FXML
    private TextField streetNumberField;
    @FXML
    private TextField streetNameField;
    @FXML
    private TextField suburbField;
    @FXML
    private TextField bedroomsField;
    @FXML
    private TextField bathroomsField;
    @FXML
    private TextField parkingSpacesField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField managingAgentField;
    @FXML
    private TextField builtDateField;

    PropertyModel model = new PropertyModel();
    String Dateformat = "yyyy-MM-dd";

    @FXML
    private void initialize() {

        // This lines of code will provide the elements to the choice box for selection.
        propertyTypeChoiceBox.getItems().setAll(Property.PropertyType.values());
        propertyTypeChoiceBox.setValue(Property.PropertyType.House);

        stateChoiceBox.getItems().setAll(Property.State.values());
        stateChoiceBox.setValue(Property.State.QLD);
    }

    @FXML
    private void printChoice() {
        // This function was just to test if the choice box is  working perfectly. 
        System.out.println(propertyTypeChoiceBox.getValue());
        System.out.println(stateChoiceBox.getValue());
    }

    @FXML
    private void createProperty() {
        // Function to create the property. 

        printChoice();
        int bathroomCount = 0; // BathroomCount is initialized to 0 to avoid error while validating integer if there is no value entered. 
        int bedroomCount = 0;// bedCount is initialized to 0 to avoid error while validating integer if there is no value entered. 
        int streetNumber = 0;// streetNumber is initialized to 0 to avoid error while validating integer if there is no value entered. 
        int parkingSpaces = 0;// parkingSpaces is initialized to 0 to avoid error while validating integer if there is no value entered. 

        if (!streetNumberField.getText().isEmpty() && isInteger(streetNumberField.getText())) {
            // This condition statement will only be executed if the streetnumber is provided and it is in integer.
            streetNumber = Integer.parseInt(streetNumberField.getText());
        }

        String streetName = streetNameField.getText(); // Storing value from text field to related variable.
        String suburb = suburbField.getText();// Storing value from text field to related variable.
        String state = stateChoiceBox.getValue().toString();// Storing value from text field to related variable.
        if (!bathroomsField.getText().isEmpty() && isInteger(bathroomsField.getText())) {
            // This condition statement will only be executed if the bathroomField is provided and it is in integer.
            bathroomCount = Integer.parseInt(bathroomsField.getText());
        }
        if (!bedroomsField.getText().isEmpty() && isInteger(bedroomsField.getText())) {
            // This condition statement will only be executed if the bedroomsField is provided and it is in integer.
            bedroomCount = Integer.parseInt(bedroomsField.getText());
        }
        if (!parkingSpacesField.getText().isEmpty() && isInteger(parkingSpacesField.getText())) {
            // This condition statement will only be executed if the parkingSpacesField is provided and it is in integer.
            parkingSpaces = Integer.parseInt(parkingSpacesField.getText());
        }
        String propertyType = propertyTypeChoiceBox.getValue().toString();// Storing value from choiceBox to related variable.
        String managingAgent = managingAgentField.getText();// Storing value from choiceBox to related variable.
        String builtDate = builtDateField.getText();// Storing value from text field to related variable.
        System.out.println(streetName); // This code was created just for testing.

        // Following conditions will check the data validation and make sure the data entered are according to the format and not null.
        if (customerIdField.getText().isEmpty()) {
            // This condition will only be satisfied if the constumer is not provided. As we know that Customer Id is not mandotory becuase there can be house not yet rented or related to any customer. 
            if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() || bathroomsField.getText().isEmpty() || bedroomsField.getText().isEmpty() || parkingSpacesField.getText().isEmpty() || managingAgentField.getText().isEmpty() || builtDateField.getText().isEmpty()) {
                //  This condition will only be executed if the required fileds are not provided. 
                displayMessage("Please Fill-up every details!");
            } else if (!isInteger(streetNumberField.getText()) || !isInteger(bathroomsField.getText()) || !isInteger(bedroomsField.getText()) || !isInteger(parkingSpacesField.getText()) || !datevalidation(builtDate, Dateformat)) {
                // This condition will only be executed if the required values are not in integer format.
                displayMessage("Please check the formats of input!");
            } else {
                // If all the data validation is passed then this statement will be executed for creating a property.  
                model.addPropertyNoId(streetName, streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType);
                displayMessage("Property created successful!\nProperty ID: "+model.getLastId());
            }
        } else {
            // if there is a Customer existing and related to the property, this conditions will be validated. 
            int customerId = Integer.parseInt(customerIdField.getText()); // To check if the customerId is integer format.
            System.out.println(customerIdField.getText());
            if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() || bathroomsField.getText().isEmpty() || bedroomsField.getText().isEmpty() || parkingSpacesField.getText().isEmpty() || managingAgentField.getText().isEmpty() || builtDateField.getText().isEmpty()) {
                // if there is any required field empty, then following message will be displayed.
                displayMessage("Please Fill-up every details!");
            } else if (!isInteger(bathroomsField.getText()) || !isInteger(bedroomsField.getText()) || !isInteger(parkingSpacesField.getText()) || !isInteger(customerIdField.getText())) {
                // If the input format of the required fields are not satisfied, then following message will be displayed.    
                displayMessage("Please check the formats of input!");
            } else if (model.findCustomer(customerId) == false) {
                // If there is no customer existing in the record, the following message will be displayed.    
                displayMessage("Please enter valid CustomerID!");
            } else {
                // if every condition is satisfied, then a proeprty will be created. 
                model.addProperty(streetNameField.getText(), streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType, customerId);
                displayMessage("Property created successful!\nProperty ID: "+model.getLastId());
            }

        }
    }

    @FXML
    private void goBack() throws IOException {
        // Function to change the scene and go back to SearchProperty FXML page.
        App.setRoot("SearchProperty");
    }

    public static boolean isInteger(String a) {
        // function to validate the data if in integer form.
        try {

            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean datevalidation(String date, String format) {
        // Function to validate the date format.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

        try {
            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public void displayMessage(String message) {
        // Function to display pop-up message. 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
