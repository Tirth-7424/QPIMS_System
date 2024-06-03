/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreatePropertyController{

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

    PropertyModel model=new PropertyModel();
    
    @FXML
    private void initialize() {

        //propertyTypeChoiceBox.setItems(propertyType);
        propertyTypeChoiceBox.getItems().setAll(Property.PropertyType.values());
        propertyTypeChoiceBox.setValue(Property.PropertyType.House);
        
        stateChoiceBox.getItems().setAll(Property.State.values());
        stateChoiceBox.setValue(Property.State.QLD);
    }

    @FXML
    private void printChoice() {
        System.out.println(propertyTypeChoiceBox.getValue());
        System.out.println(stateChoiceBox.getValue());
    }

    @FXML
    private void createProperty() {
        printChoice();
        int streetNumber = Integer.parseInt(streetNumberField.getText());
        String streetName = streetNameField.getText();
        String suburb = suburbField.getText();
        String state = stateChoiceBox.getValue().toString();
        int bathroomCount = Integer.parseInt(bathroomsField.getText());
        int bedroomCount = Integer.parseInt(bedroomsField.getText());
        int parkingSpaces = Integer.parseInt(parkingSpacesField.getText());
        String propertyType = propertyTypeChoiceBox.getValue().toString();
        String managingAgent = managingAgentField.getText();
        String builtDate = builtDateField.getText();
        System.out.println(streetName);
        if (customerIdField.getText().isEmpty()) {
            if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() || bathroomsField.getText().isEmpty() || bedroomsField.getText().isEmpty() || parkingSpacesField.getText().isEmpty() || managingAgentField.getText().isEmpty() || builtDateField.getText().isEmpty()) {
                displayMessage("Please Fill-up every details!");
            } else if (!isInteger(bathroomsField.getText()) || !isInteger(bedroomsField.getText()) || !isInteger(parkingSpacesField.getText())) {
                displayMessage("Please check the formats of input!");
            } else {
                model.addPropertyNoId(streetName, streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType);
                displayMessage("Property created successful!");
            }
        } else {
            int customerId = Integer.parseInt(customerIdField.getText());
            System.out.println(customerIdField.getText());
            if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() || bathroomsField.getText().isEmpty() || bedroomsField.getText().isEmpty() || parkingSpacesField.getText().isEmpty() || managingAgentField.getText().isEmpty() || builtDateField.getText().isEmpty()) {
                displayMessage("Please Fill-up every details!");
            } else if (!isInteger(bathroomsField.getText()) || !isInteger(bedroomsField.getText()) || !isInteger(parkingSpacesField.getText()) || !isInteger(customerIdField.getText())) {
                displayMessage("Please check the formats of input!");
            } else if (model.findCustomer(customerId) == false) {
                displayMessage("Please enter valid CustomerID!");
            } else {
                model.addProperty(streetNameField.getText(), streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType, customerId);
                displayMessage("Property created successful!");
            }

        }
    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("SearchProperty");
    }
    
       public static boolean isInteger(String a) {
        try {
           
            Integer.parseInt(a);
           return true;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
    
      public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}