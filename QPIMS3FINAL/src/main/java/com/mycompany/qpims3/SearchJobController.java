/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
/*
SearchJobController is associated with SearchJob FXML page. This page provides Search ability around the jobs saved in database server.
SearchJobController works for two panes, first is search pane and another is update pane. Search is active whereas update pane
is only acitve when a entry is selected from list view.
*/
package com.mycompany.qpims3;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author tirth
 */
public class SearchJobController implements Initializable {


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
    private ListView<?> listview;
    @FXML
    private Button SearchByAddbtn;
    @FXML
    private TextField PropertyIDtxt;
    /**
     * Initializes the controller class.
     */
    
     JobModel model=new JobModel();
     private final ObservableList<Job> JobList =
	      FXCollections.observableArrayList();
     List< Property > results;
     int numberOfEntries;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void goBack() throws IOException {
        App.setRoot("MainMenuFXML");
    }
@FXML
    private void CreateJob() throws IOException {
        App.setRoot("CreateJob");
    }
    
/*@FXML
      private void FindPropertyByID(ActionEvent event){
       StreetNumbertxt.clear();
       StreetNametxt.clear();
       Suburbtxt.clear();
       Typetxt.clear();
       
      
        String foundID= PropertyIDtxt.getText();
        
        if(foundID.isEmpty()){
        displayMessage("Please Enter a ID!!");
        }
        else{
            
     if(isInteger(foundID)){
       int id = Integer.parseInt(foundID);
        results = model.getPropertiesByID(id);

        numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
       //listview.setItems(PropertyList); // bind to contactsList
      // listview.setCellFactory(param -> new PropertyListID());   
       getPropertiesbyID(id);   
       listview.getSelectionModel().selectedItemProperty().addListener(
        (var observableValue, var oldValue, var newValue) -> {
        if (newValue != null) {
            
              
    }
        }
       );
      }
      else{
        displayMessage("Not found");
        PropertyIDtxt.clear();
       
        
      }
     }
     else{
     displayMessage("ID should be Integer.");
     }
    }
      } */
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
 
 /*public void getPropertiesbyID(int a){
   
   JobList.setAll(model.getJobByID(a));
   
   }*/
    private static class PropertyListID extends ListCell<Property> {
        @Override
        protected void updateItem(Property property, boolean empty) {
            super.updateItem(property, empty);
            if (empty || property == null) {
                setText(null);
            } else {
                setText(property.getId() + " " + property.getStreetNumber()+ " " + property.getStreetName()+ " " +property.getState()+" "+property.getBathroomCount()+" "+property.getBedroomCount());
            }
        }
   }
    

}
