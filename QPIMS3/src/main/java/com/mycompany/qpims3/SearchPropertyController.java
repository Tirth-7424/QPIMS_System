/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author tirth
 */
public class SearchPropertyController implements Initializable {

    @FXML
    private Button SearchByIDbtn;
    @FXML
    private Button Backbtn;
    @FXML
    private TextField StreetNumbertxt;
    @FXML
    private Button CreatePropertybtn;
    @FXML
    private TextField StreetNametxt;
    @FXML
    private Button updateButton;
    @FXML
    private TextField Suburbtxt;
    @FXML
    private TextField Typetxt;
    @FXML
    private ListView<Property> listview;
    @FXML
    private Button SearchByAddbtn;
    @FXML
    private TextField PropertyIDtxt;
    @FXML
    private Button secondaryButton;
    @FXML
    private TextField streetNumberField;
    @FXML
    private TextField streetNameField;
    @FXML
    private TextField suburbField;
    @FXML
    private Button secondaryButton1;
    @FXML
    private TextField bedroomsField;
    @FXML
    private TextField bathroomsField;
    @FXML
    private TextField parkingSpacesField;
    @FXML
    private TextField builtDateField;
    @FXML
    private ChoiceBox propertyTypeChoiceBox;
    @FXML
    private ChoiceBox stateChoiceBox;
    @FXML
    private TextField managingAgentField;
    @FXML
    private TextField customerIdField;
    @FXML
    private AnchorPane updatePropertyPane;
    @FXML
    private AnchorPane searchPropertyPane;
    /**
     * Initializes the controller class.
     */

    PropertyModel model = new PropertyModel();
    private final ObservableList<Property> PropertyList
            = FXCollections.observableArrayList();
    List< Property> results;
    List<Property> allProperties;
    Property updateProperty;
    int numberOfEntries;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        propertyTypeChoiceBox.getItems().setAll(Property.PropertyType.values());

        stateChoiceBox.getItems().setAll(Property.State.values());

    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("MainMenuFXML");
    }

    @FXML
    private void CreateProperty() throws IOException {
        App.setRoot("PropertyCreateFXML");
    }

    @FXML
    private void FindPropertyByID(ActionEvent event) {
        StreetNumbertxt.clear();
        StreetNametxt.clear();
        Suburbtxt.clear();
        Typetxt.clear();
        updateButton.setDisable(true);

        String foundID = PropertyIDtxt.getText();

        if (foundID.isEmpty()) {
            displayMessage("Please Enter a ID!!");
        } else {

            if (isInteger(foundID)) {
                int id = Integer.parseInt(foundID);
                results = model.getPropertiesByID(id);

                numberOfEntries = results.size();
                if (numberOfEntries != 0) {
                    listview.setItems(PropertyList);
                    listview.setCellFactory(param -> new PropertyListID());
                    getPropertiesbyID(id);
                    listview.getSelectionModel().selectedItemProperty().addListener(
                            (var observableValue, var oldValue, var newValue) -> {
                                if (newValue != null) {
                                    updateProperty = newValue;
                                    setfields(updateProperty);
                                    updateButton.setDisable(false);

                                }
                            }
                    );
                } else {
                    displayMessage("No entries found! Please try with different number.");
                    PropertyIDtxt.clear();
                }
            } else {
                displayMessage("Please enter integer!");
                PropertyIDtxt.clear();
            }
        }
    }

    @FXML
    private void UpdateProperty() {

        printChoice();
        int PID = updateProperty.getCustomerID();
        int bathroomCount = 0;
        int bedroomCount = 0;
        int streetNumber = 0;
        int parkingSpaces = 0;
        if(!streetNumberField.getText().isEmpty()  && isInteger(streetNumberField.getText())){
         streetNumber = Integer.parseInt(streetNumberField.getText());}
        String streetName = streetNameField.getText();
        String suburb = suburbField.getText();
        String state = stateChoiceBox.getValue().toString();
        if(!bathroomsField.getText().isEmpty() &&  isInteger(bathroomsField.getText())){
         bathroomCount = Integer.parseInt(bathroomsField.getText());}
        if(!bedroomsField.getText().isEmpty() && isInteger(bedroomsField.getText())){
         bedroomCount = Integer.parseInt(bedroomsField.getText());}
        if(!parkingSpacesField.getText().isEmpty() && isInteger(parkingSpacesField.getText())){
         parkingSpaces = Integer.parseInt(parkingSpacesField.getText());}
        String propertyType = propertyTypeChoiceBox.getValue().toString();
        String managingAgent = managingAgentField.getText();
        String builtDate = builtDateField.getText();
        System.out.println(streetName);
        if (Integer.parseInt(customerIdField.getText()) == 0) {
            if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() || bathroomsField.getText().isEmpty() || bedroomsField.getText().isEmpty() || parkingSpacesField.getText().isEmpty() || managingAgentField.getText().isEmpty() || builtDateField.getText().isEmpty()) {
                displayMessage("Please Fill-up every details!");
            } else if (!isInteger(streetNumberField.getText()) || !isInteger(bathroomsField.getText()) || !isInteger(bedroomsField.getText()) || !isInteger(parkingSpacesField.getText())) {
                displayMessage("Please check the formats of input!");
            } else {
                model.updatePropertyNoId(PID, streetName, streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType);
                displayMessage("Property Updated successful!");
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
                model.updateProperty(PID, streetNameField.getText(), streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType, customerId);
                displayMessage("Property Update successful!");
            }

        }
    }

    @FXML
    private void goBack2() throws IOException {
        App.setRoot("SearchProperty");
    }

    @FXML
    private void updatePropertySwitch(ActionEvent event) throws IOException {
        searchPropertyPane.setVisible(false);
        updatePropertyPane.setVisible(true);

    }

    @FXML
    private void searchProperty() {
        System.out.println("Called searchProperty");
        PropertyIDtxt.clear();
        updateButton.setDisable(true);
        int foundNumber = 0;
        String foundName = StreetNametxt.getText();
        if (!StreetNumbertxt.getText().isEmpty()) {
            foundNumber = Integer.parseInt(StreetNumbertxt.getText());
        }

        String Suburb = Suburbtxt.getText();
        String Type = Typetxt.getText();
        allProperties = model.getAllProperties();
        System.out.println(allProperties);
        System.out.println("Index 0: " + allProperties.get(0).getStreetName());
        if (!StreetNametxt.getText().isEmpty()) {
            for (int i = 0; i < allProperties.size(); i++) {
                if (!allProperties.get(i).getStreetName().equals(foundName)) {
                    System.out.println("Removing: " + allProperties.get(i).getStreetName());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!StreetNumbertxt.getText().isEmpty()) {
            for (int i = 0; i < allProperties.size(); i++) {
                if (allProperties.get(i).getStreetNumber() != foundNumber) {
                    System.out.println("Removing: " + allProperties.get(i).getStreetNumber());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!Suburb.isEmpty()) {
            for (int i = 0; i < allProperties.size(); i++) {
                if (!allProperties.get(i).getSuburb().equals(Suburb)) {
                    System.out.println("Removing: " + allProperties.get(i).getSuburb());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!Type.isEmpty()) {
            for (int i = 0; i < allProperties.size(); i++) {
                if (!allProperties.get(i).getPropertyType().equals(Type)) {
                    System.out.println("Removing: " + allProperties.get(i).getPropertyType());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!allProperties.isEmpty()) {
            System.out.println(allProperties);
        } else {

            displayMessage("No Property Found");
            clearfields();
        }
        numberOfEntries = allProperties.size();
        if (numberOfEntries != 0) {
            listview.setItems(PropertyList);
            listview.setCellFactory(param -> new PropertyListID());
            getProperties(allProperties);
            listview.getSelectionModel().selectedItemProperty().addListener(
                    (var observableValue, var oldValue, var newValue) -> {
                        if (newValue != null) {
                            updateProperty = newValue;
                            setfields(updateProperty);
                            updateButton.setDisable(false);

                        }
                    }
            );
        }
    }

    public void setfields(Property newValue) {
        streetNumberField.setText("" + newValue.getStreetNumber());
        streetNameField.setText(newValue.getStreetName());
        suburbField.setText(newValue.getSuburb());
        stateChoiceBox.setValue(Property.State.valueOf(newValue.getState()));
        propertyTypeChoiceBox.setValue(Property.PropertyType.valueOf(newValue.getPropertyType()));
        bathroomsField.setText("" + newValue.getBathroomCount());
        bedroomsField.setText("" + newValue.getBedroomCount());
        parkingSpacesField.setText("" + newValue.getParkingSpaces());
        managingAgentField.setText(newValue.PropertyManager());
        builtDateField.setText("" + newValue.getBuiltDate());
        customerIdField.setText("" + newValue.getCustomerID());
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

    public void getPropertiesbyID(int a) {

        PropertyList.setAll(model.getPropertiesByID(a));

    }

    public void getProperties(List<Property> a) {

        PropertyList.setAll(a);

    }

    public void clearfields() {

        StreetNumbertxt.clear();
        StreetNametxt.clear();
        Suburbtxt.clear();
        Typetxt.clear();
        PropertyIDtxt.clear();
    }

    private void printChoice() {
        System.out.println(propertyTypeChoiceBox.getValue());
        System.out.println(stateChoiceBox.getValue());
    }

    private static class PropertyListID extends ListCell<Property> {

        @Override
        protected void updateItem(Property property, boolean empty) {
            super.updateItem(property, empty);
            if (empty || property == null) {
                setText(null);
            } else {

                setText("PropertyID:" + property.getId() + ", StreetNumber:" + property.getStreetNumber() + ", StreetName:" + property.getStreetName() + ", Suburb:" + property.getSuburb() + ", State:" + property.getState() + ", Bathrooms:" + property.getBathroomCount() + ", Bedrooms::" + property.getBedroomCount() + ", PropertyType:" + property.getPropertyType() + ", PropertyManager:" + property.PropertyManager() + ", Customer:" + property.getCustomerID() + ", BuiltDate:" + property.getBuiltDate());
            }
        }
    }
    


}
