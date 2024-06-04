/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;

import java.io.IOException;
import static java.lang.Double.parseDouble;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateJobController {

    @FXML
    private TextField bookingDateField;
    @FXML
    private TextField chargeField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ChoiceBox jobStatusChoiceBox;
    @FXML
    private ChoiceBox jobTypeChoiceBox;
    @FXML
    private TextField propertyIdField;
    @FXML
    private TextField serviceStaffField;

    JobModel model = new JobModel();

    @FXML
    private void initialize() {

        jobTypeChoiceBox.getItems().setAll(Job.JobType.values());
        jobTypeChoiceBox.setValue(Job.JobType.Cleaning);

        jobStatusChoiceBox.getItems().setAll(Job.JobStatus.values());
        jobStatusChoiceBox.setValue(Job.JobStatus.Ongoing);
    }

    @FXML
    private void printChoice() {
        System.out.println(jobTypeChoiceBox.getValue());
        System.out.println(jobStatusChoiceBox.getValue());
    }

    @FXML
    private void createJob() {
        printChoice();
        
        int propertyId = Integer.parseInt(propertyIdField.getText());
        String bookingDate = bookingDateField.getText();
        double charge = parseDouble(chargeField.getText());
        String serviceStaffName = serviceStaffField.getText();
        String description = descriptionField.getText();
        String jobType = jobTypeChoiceBox.getValue().toString();
        String jobStatus = jobTypeChoiceBox.getValue().toString();
        /*if (customerIdField.getText().isEmpty()) {
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

        }*/

    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("JobSearch");
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
