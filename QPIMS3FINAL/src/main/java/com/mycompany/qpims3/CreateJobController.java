/*
The CreateJobController class is used by CreateJob.fxml. It is responsible for
taking user input and calling appropriate JobModel methods to create new Job
entries.
 */
package com.mycompany.qpims3;

import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateJobController {

    @FXML
    private TextField bookingDatetxt;
    @FXML
    private TextField completionDatetxt;
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

    JobModel jm = new JobModel();
    String Dateformat = "yyyy-MM-dd";

    @FXML
    private void initialize() {
        // Populate choiceboxes with job type and job status enums declared in Job class
        jobTypeChoiceBox.getItems().setAll(Job.JobType.values());
        jobTypeChoiceBox.setValue(Job.JobType.Cleaning);

        jobStatusChoiceBox.getItems().setAll(Job.JobStatus.values());
        jobStatusChoiceBox.setValue(Job.JobStatus.Ongoing);
    }

    @FXML
    private void createJob() {
        int propertyId = 0;
        double charge = 0.0;
        String completionDate = null;
        String bookingDate = null;
        if (!propertyIdField.getText().isEmpty() && isInteger(propertyIdField.getText())) {
            propertyId = Integer.parseInt(propertyIdField.getText());
        }
        if ((!chargeField.getText().isEmpty() && isDouble(chargeField.getText()))) {
            charge = Double.parseDouble(chargeField.getText());
        }

        if (!bookingDatetxt.getText().isEmpty()) {
            bookingDate = bookingDatetxt.getText();
        }

        if (!completionDatetxt.getText().isEmpty()) {
            completionDate = completionDatetxt.getText();
        }

        String serviceStaffName = serviceStaffField.getText();
        String description = descriptionField.getText();
        String jobType = jobTypeChoiceBox.getValue().toString();
        String jobStatus = jobStatusChoiceBox.getValue().toString();

        if (!propertyIdField.getText().isEmpty()) {
            if (jm.findProperty(propertyId)) {
                if (!bookingDatetxt.getText().isEmpty() && dateValidation(bookingDate, Dateformat)) {
                    // Completion date is optional, so this check ensures that jobs can be updated without errors
                    if (!completionDatetxt.getText().isEmpty() && dateValidation(completionDatetxt.getText(), Dateformat)) {
                        if (isDouble(chargeField.getText()) && !serviceStaffName.isEmpty() && !description.isEmpty()) {
                            jm.addJob(description, bookingDate, completionDate, charge, serviceStaffName, jobType, jobStatus, propertyId);
                            displayMessage("Job created successfully!");
                            clearfields();
                        } else {
                            displayMessage("Please make sure the format of inputs and provide every data!");
                        }

                    } else {

                        if (isDouble(chargeField.getText()) && !serviceStaffName.isEmpty() && !description.isEmpty()) {
                            jm.addJob(description, bookingDate, completionDate, charge, serviceStaffName, jobType, jobStatus, propertyId);
                            displayMessage("Job created successfully!");
                            clearfields();
                        } else {
                            displayMessage("Please make sure the format of inputs and provide every data!");
                        }

                    }
                } else {
                    displayMessage("Please check Start Date format and make sure it is provided!");
                }
            } else {
                displayMessage("Please provide correct property Id!");
            }
        } else {
            displayMessage("Please enter property Id!");
        }

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

    public static boolean isDouble(String a) {

        if (a == null || a.trim().isEmpty()) {
            return false;
        }
        try {

            Double.parseDouble(a.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

    public void clearfields() {
        bookingDatetxt.clear();
        completionDatetxt.clear();
        chargeField.clear();
        descriptionField.clear();
        propertyIdField.clear();
        serviceStaffField.clear();
    }

    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
