package com.mycompany.guiprototype;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private TextField bedroomField;
    @FXML
    private TextField bathroomField;
    @FXML
    private TextField parkingSpacesField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField managingAgentField;
    @FXML
    private TextField buildDateField;

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
        int id = 1;
        int streetNumber = Integer.parseInt(streetNumberField.getText());
        String streetName = streetNameField.getText();
        String suburb = suburbField.getText();
        String state = stateChoiceBox.getValue().toString();
        int bathroomCount = Integer.parseInt(bathroomField.getText());
        int bedroomCOunt = Integer.parseInt(bedroomField.getText());
        int parkingSpaces = Integer.parseInt(parkingSpacesField.getText());
        String propertyType = propertyTypeChoiceBox.getValue().toString();
        String managingAgent = managingAgentField.getText();
        String builtDate = buildDateField.getText();
        int customerId = Integer.parseInt(customerIdField.getText());
        
        
    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("propertySearch");
    }
}
