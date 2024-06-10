/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

/*
SearchPropertyController is associated with SearchProperty FXML page. This page provides Search ability around the properties saved in database server.
SearchPropertyController works for two panes, first is search pane and another is update pane. Search is active whereas update pane
is only acitve when a entry is selected from list view.
*/
package com.mycompany.qpims3;
// Below are the imports help in fetching library and thier elements to be used in application.

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
// Below are the elements used in developing the SearchProperty FXML page.

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
    String Dateformat = "yyyy-MM-dd";
    PropertyModel model = new PropertyModel(); // Initializing the property model.
    private final ObservableList<Property> PropertyList //Initializing a odservableList object of property type.
            = FXCollections.observableArrayList();
    List< Property> results; // Initializing list named results of property type.
    List<Property> allProperties; // Initializing list named allproperties of property type.
    Property updateProperty; // Initializing a property object named updateProperty.
    int numberOfEntries; // Initializing a variable of int type named numberOfEnteries.

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Following lines of code will fill the choice box with there enum variables.
        propertyTypeChoiceBox.getItems().setAll(Property.PropertyType.values());

        stateChoiceBox.getItems().setAll(Property.State.values());

    }

    @FXML
    private void goBack() throws IOException {
        // Function to change the scene to MainMenu.
        App.setRoot("MainMenuFXML");
    }

    @FXML
    private void CreateProperty() throws IOException {
        // Function to change the scene to PropertyCreate.
        App.setRoot("PropertyCreateFXML");
    }

    @FXML
    private void FindPropertyByID(ActionEvent event) {
        // Function to find the Property by using Property ID.
        listview.getItems().clear();
        StreetNumbertxt.clear();
        StreetNametxt.clear();
        Suburbtxt.clear();
        Typetxt.clear();
        updateButton.setDisable(true); // Setting the update button to disable as we only want update button enable if any entry from list view is selected.

        String foundID = PropertyIDtxt.getText(); // Storing the ID from text field to an appropriate variable.

        if (foundID.isEmpty()) {
            // If no ID is provided i.e. text field is left empty, the following message will be displayed.
            displayMessage("Please Enter a ID!!");
        } else {

            if (isInteger(foundID)) {
                // If the ID is provided, following lines of code will be executed.

                int id = Integer.parseInt(foundID); // Storing Id in a suitable integer variable.
                results = model.getPropertiesByID(id); // Providing id as a parameter to getPropertiesByID method in model and storing the outcome in results list.

                numberOfEntries = results.size(); // Determine the size of the list.
                if (numberOfEntries != 0) {
                    // Following lines of code will be executed if there are enteries found.

                    //Following is code to set-up the enteries to list view.
                    listview.setItems(PropertyList);
                    listview.setCellFactory(param -> new PropertyListID());
                    getPropertiesbyID(id);
                    listview.getSelectionModel().selectedItemProperty().addListener(
                            (var observableValue, var oldValue, var newValue) -> {
                                if (newValue != null) {
                                    updateProperty = newValue; // Assigning the selected value to updateProperty object.
                                    setfields(updateProperty); // Set-up the details of selected object on update page.
                                    updateButton.setDisable(false); // Update button will be enabled.

                                }
                            }
                    );
                } else {
                    // Following message will be pop-up if there is no entry found.
                    displayMessage("No entries found! Please try with different number.");
                    PropertyIDtxt.clear();
                }
            } else {
                // Following message will be pop-up if there the ID provided as an input in not integer.
                displayMessage("Please enter integer!");
                PropertyIDtxt.clear();
            }
        }
    }

    @FXML
    private void UpdateProperty() {
        printChoice(); // Print choice funtion developed just to test the outcome.
        int PID = updateProperty.getCustomerID(); // Property ID is fetched using the getter method.
        int bathroomCount = 0; // Initilazed to 0 to not to encounter error while parsing or perfoming data validation incase there is no value provided.
        int bedroomCount = 0; // Initilazed to 0 to not to encounter error while parsing or perfoming data validation incase there is no value provided.
        int streetNumber = 0; // Initilazed to 0 to not to encounter error while parsing or perfoming data validation incase there is no value provided.
        int parkingSpaces = 0; // Initilazed to 0 to not to encounter error while parsing or perfoming data validation incase there is no value provided.
        if (!streetNumberField.getText().isEmpty() && isInteger(streetNumberField.getText())) {
            // If the street number is provided and is in the integer form, the street number will be stored in a variable.
            streetNumber = Integer.parseInt(streetNumberField.getText());
        }
        String streetName = streetNameField.getText();
        String suburb = suburbField.getText();
        String state = stateChoiceBox.getValue().toString();
        if (!bathroomsField.getText().isEmpty() && isInteger(bathroomsField.getText())) {
            // If the bathroom count is provided and is in the integer form, the bathroom count will be stored in a variable.
            bathroomCount = Integer.parseInt(bathroomsField.getText());
        }
        if (!bedroomsField.getText().isEmpty() && isInteger(bedroomsField.getText())) {
            // If the bedroom count is provided and is in the integer form, the bedroom count will be stored in a variable.
            bedroomCount = Integer.parseInt(bedroomsField.getText());
        }
        if (!parkingSpacesField.getText().isEmpty() && isInteger(parkingSpacesField.getText())) {
            // If the parking space is provided and is in the integer form, the parking space will be stored in a variable.
            parkingSpaces = Integer.parseInt(parkingSpacesField.getText());
        }
        String propertyType = propertyTypeChoiceBox.getValue().toString();
        String managingAgent = managingAgentField.getText();
        String builtDate = builtDateField.getText();
        System.out.println(streetName);

        // Following are the conditions for data validation.
        if (Integer.parseInt(customerIdField.getText()) == 0) {
            if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() || bathroomsField.getText().isEmpty() || bedroomsField.getText().isEmpty() || parkingSpacesField.getText().isEmpty() || managingAgentField.getText().isEmpty() || builtDateField.getText().isEmpty()) {
                // if some details are not provided.
                displayMessage("Please Fill-up every details!");
            } else if (!isInteger(streetNumberField.getText()) || !isInteger(bathroomsField.getText()) || !isInteger(bedroomsField.getText()) || !isInteger(parkingSpacesField.getText()) /*|| dateValidation(builtDateField.getText(), Dateformat)*/ ) {
                //If format of the details are not correct.
                displayMessage("Please check the formats of input!");
            } else {
                //If all the steps are verified and validate, the property will be added.
                model.updatePropertyNoId(PID, streetName, streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType);
                displayMessage("Property Updated successful!");
            }
        } else {
            // If property needs to be associated with the customer ID, following lines of code will be executed.
            int customerId = Integer.parseInt(customerIdField.getText()); // Validating the customer ID.
            System.out.println(customerIdField.getText());
            if (streetNumberField.getText().isEmpty() || streetNameField.getText().isEmpty() || suburbField.getText().isEmpty() || bathroomsField.getText().isEmpty() || bedroomsField.getText().isEmpty() || parkingSpacesField.getText().isEmpty() || managingAgentField.getText().isEmpty() || builtDateField.getText().isEmpty()) {
                // If some details are not provided, the following message will be pop-up.
                displayMessage("Please Fill-up every details!");
            } else if (!isInteger(streetNumberField.getText()) || !isInteger(bathroomsField.getText()) || !isInteger(bedroomsField.getText()) || !isInteger(parkingSpacesField.getText()) || !isInteger(customerIdField.getText()) ) {
                //If format of the details are not correct.) {
                // If format of the provided imput is not correct.
                displayMessage("Please check the formats of input!");
            } else if (model.findCustomer(customerId) == false) {
                // if the customer does not exists.
                displayMessage("Please enter valid CustomerID!");
            } else {
                // When every data is validate, the property will be created.
                model.updateProperty(PID, streetNameField.getText(), streetNumber, suburb, state, builtDate, bathroomCount, bedroomCount, parkingSpaces, managingAgent, propertyType, customerId);
                displayMessage("Property Update successful!");
            }

        }
    }

    @FXML
    private void goBack2() throws IOException {
        // Function to change the scene to SearchProperty.
        App.setRoot("SearchProperty");
    }

    @FXML
    private void updatePropertySwitch(ActionEvent event) throws IOException {
        // Switching from search property pane to update property pane.
        searchPropertyPane.setVisible(false);
        updatePropertyPane.setVisible(true);

    }

    @FXML
    private void searchProperty() {
        listview.getItems().clear();
        System.out.println("Called searchProperty"); // Just  for conformation.
        PropertyIDtxt.clear();
        updateButton.setDisable(true);
        int foundNumber = 0;
        String foundName = StreetNametxt.getText();
        if (!StreetNumbertxt.getText().isEmpty()) {
            foundNumber = Integer.parseInt(StreetNumbertxt.getText());
        }

        String Suburb = Suburbtxt.getText();
        String Type = Typetxt.getText();
        allProperties = model.getAllProperties(); //Storing all the property entries in one list.
        System.out.println(allProperties); // Printing on console just for conformation

        // Following are the conditions to eleminate the properties whose details does not match. In other words, just keeping the enteries whose details matches.
        if (!StreetNametxt.getText().isEmpty()) {
            // Street name is provided, following loop will be executed.
            for (int i = 0; i < allProperties.size(); i++) {
                //Eliminating the records from allproperties list if the streetName doesn't match.
                if (!allProperties.get(i).getStreetName().equals(foundName)) {
                    System.out.println("Removing: " + allProperties.get(i).getStreetName());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!StreetNumbertxt.getText().isEmpty()) {
            for (int i = 0; i < allProperties.size(); i++) {
                //Eliminating the records from allproperties list if the streetNumber doesn't match.
                if (allProperties.get(i).getStreetNumber() != foundNumber) {
                    System.out.println("Removing: " + allProperties.get(i).getStreetNumber());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!Suburb.isEmpty()) {
            for (int i = 0; i < allProperties.size(); i++) {
                //Eliminating the records from allproperties list if the suburb doesn't match.
                if (!allProperties.get(i).getSuburb().equals(Suburb)) {
                    System.out.println("Removing: " + allProperties.get(i).getSuburb());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!Type.isEmpty()) {
            for (int i = 0; i < allProperties.size(); i++) {
                //Eliminating the records from allproperties list if the property type doesn't match.
                if (!allProperties.get(i).getPropertyType().equals(Type)) {
                    System.out.println("Removing: " + allProperties.get(i).getPropertyType());
                    allProperties.remove(i);
                    i--;
                }
            }
        }
        if (!allProperties.isEmpty()) {
            // Now, finally, if the modified allproperties list is having some value, the value will be printed on console for testing.
            System.out.println(allProperties);
        } else {
            // If no entry is found, the following message will be displayed.
            displayMessage("No Property Found");
            clearfields();
        }
        numberOfEntries = allProperties.size();
        if (numberOfEntries != 0) {
            // If there is property found on the basis of search information provided, following lines of code will help in displaying over list view.
            listview.setItems(PropertyList);
            listview.setCellFactory(param -> new PropertyListID());
            getProperties(allProperties);
            listview.getSelectionModel().selectedItemProperty().addListener(
                    (var observableValue, var oldValue, var newValue) -> {
                        if (newValue != null) {
                            updateProperty = newValue; // The selected property entry is associated to updateProperty.
                            setfields(updateProperty); // The selected property is set on update pane.
                            updateButton.setDisable(false); // Update is active once the entry is selected. 

                        }
                    }
            );
        }
    }

    public void setfields(Property newValue) {
        // Method to set the fields on update property pane. It is only called when a property is selected.
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
        //Function to display a customized pop-up message.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isInteger(String a) {
        // Function to validate the integer input provided.
        try {

            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void getPropertiesbyID(int a) {
        // Setting all the matched data on propertyList.
        PropertyList.setAll(model.getPropertiesByID(a));

    }

    public void getProperties(List<Property> a) {

        PropertyList.setAll(a);

    }

    public void clearfields() {
// Clear fields.
        StreetNumbertxt.clear();
        StreetNametxt.clear();
        Suburbtxt.clear();
        Typetxt.clear();
        PropertyIDtxt.clear();
    }

    private void printChoice() {
        // Print Choice, just for testing purpose.
        System.out.println(propertyTypeChoiceBox.getValue());
        System.out.println(stateChoiceBox.getValue());
    }
    
    public static boolean dateValidation(String date, String format) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

        try {
            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    

    private static class PropertyListID extends ListCell<Property> {
// A method similar to toString but this is for list view.

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
