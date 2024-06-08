/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author tirth
 */
public class JobSearchController implements Initializable {


    @FXML
    private AnchorPane searchJobPane;
    @FXML
    private AnchorPane updateJobPane;
    @FXML
    private Button searchByPropertyIdButton;
    @FXML
    private Button Backbtn;
    @FXML
    private TextField searchJobIdField;
    @FXML
    private Button createJobButton;
    @FXML
    private Button updateBookingButton;
    @FXML
    private TextField searchPropertyIdField;
    @FXML
    private ListView<Job> listview;
    @FXML
    private Button searchByJobIdButton;
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
    String Dateformat= "yyyy-MM-dd";
    
     List< Job > results;
    int numberOfEntries;
    private final ObservableList<Job> JobList =
	      FXCollections.observableArrayList();
    Job updateJob;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        jobTypeChoiceBox.getItems().setAll(Job.JobType.values());
        jobTypeChoiceBox.setValue(Job.JobType.Cleaning);

        jobStatusChoiceBox.getItems().setAll(Job.JobStatus.values());
        jobStatusChoiceBox.setValue(Job.JobStatus.Ongoing);
    } 
    
    @FXML
    private void goBack() throws IOException {
        App.setRoot("MainMenuFXML");
    }
    
    @FXML
    private void switchToCreateJob() throws IOException {
        App.setRoot("CreateJob");
    }
    
    @FXML
    private void updateJobSwitch(ActionEvent event) throws IOException {
        searchJobPane.setVisible(false);
        updateJobPane.setVisible(true);

    }
    @FXML
    private void searchJobSwitch(ActionEvent event) throws IOException {
        searchJobPane.setVisible(true);
        updateJobPane.setVisible(false);
    }
    
    @FXML
      private void searchJobByJobId(ActionEvent event){
       
       updateBookingButton.setDisable(true);
      
        String foundID= searchJobIdField.getText();
        
        if(foundID.isEmpty()){
        displayMessage("Please Enter a ID!!");
        }
        else{
            
     if(isInteger(foundID)){
       int id = Integer.parseInt(foundID);
        results = jm.getJobsByJobID(id);

        numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
       listview.setItems(JobList); // bind to contactsList
       listview.setCellFactory(param -> new JobListID());   
       getJobsByID(id);   
       listview.getSelectionModel().selectedItemProperty().addListener(
        (var observableValue, var oldValue, var newValue) -> {
        if (newValue != null) {
            
            updateJob = newValue;
           setfields(updateJob);
            updateBookingButton.setDisable(false);
              
    }
        }
       );
      }
      else{
        displayMessage("Not found");
        searchJobIdField.clear();
        searchPropertyIdField.clear();
        
      }
     }
     else{
     displayMessage("ID should be Integer.");
     searchJobIdField.clear();
     searchPropertyIdField.clear();
     }
    }
      }
    
    
    @FXML
    private void updateJob() {
        printChoice();
        int propertyId = 0;
        double charge = 0.0;
        String completionDate = null;
        String bookingDate = null;
        int JobId = updateJob.getJobId();
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

                    if (!completionDatetxt.getText().isEmpty() && dateValidation(completionDatetxt.getText(), Dateformat)) {
                        if(isDouble(chargeField.getText()) && !serviceStaffName.isEmpty() && !description.isEmpty()){
                        jm.updateJob(description, bookingDate, completionDate, charge, serviceStaffName, jobType, jobStatus, propertyId, JobId);
                        displayMessage("Job updated successfully!");
                        clearfields();
                        }else{
                          displayMessage("Please make sure the format of inputs and provide every data!");  
                        }

                    } else {
                        
                        if(isDouble(chargeField.getText()) && !serviceStaffName.isEmpty() && !description.isEmpty()){
                        jm.updateJob(description, bookingDate, completionDate, charge, serviceStaffName, jobType, jobStatus, propertyId, JobId);
                        displayMessage("Job updated successfully!");
                        clearfields();
                        } 
                        else{
                        displayMessage("Please make sure the format of inputs and provide every data!"); } 
                        

                    

                } }else {
                    displayMessage("Please check Start Date format and make sure it is provided!");
                }
            } else {
                displayMessage("Please provide correct property Id!");
            }
        } else {
            displayMessage("Please enter property Id!");
        }

    }
    
    public void getJobsByID(int a){
   
   JobList.setAll(jm.getJobsByJobID(a));
   
   }
    
  public void setfields(Job newValue){
      if(newValue.getCompletionDate() != null){
      completionDatetxt.setText(""+newValue.getCompletionDate());
      }
          propertyIdField.setText(""+ newValue.getPropertyId());
          bookingDatetxt.setText(""+newValue.getBookingDate());
          chargeField.setText(""+newValue.getCharge());
          serviceStaffField.setText(newValue.getServiceStaffName());
          descriptionField.setText(newValue.getDescription());
          jobTypeChoiceBox.setValue(Job.JobType.valueOf(newValue.getJobType()));
          jobStatusChoiceBox.setValue(Job.JobStatus.valueOf(newValue.getJobStatus())); 
          
      
   } 
    
   private static class JobListID extends ListCell<Job> {
        @Override
        protected void updateItem(Job job, boolean empty) {
            super.updateItem(job, empty);
            if (empty || job == null) {
                setText(null);
            } else {
                setText("JobID: "+job.getJobId()+ " JobStatus: "+job.getJobStatus()+ " JobDescription: " + job.getDescription()+ " ServiceMan: "+ job.getServiceStaffName()+ " StartDate: "+job.getBookingDate() +" CompletionDate: "+job.getCompletionDate()+" JobCost: "+job.getCharge());
            }
        }
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
        
        if(a ==null || a.trim().isEmpty()){
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

    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void printChoice() {
        System.out.println(jobTypeChoiceBox.getValue());
        System.out.println(jobStatusChoiceBox.getValue());
    }
    
    public void clearfields(){
    bookingDatetxt.clear();
    completionDatetxt.clear();
    chargeField.clear();
    descriptionField.clear();
    propertyIdField.clear();
    serviceStaffField.clear();
    }
    
}
