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
    private ListView<?> listview;
    @FXML
    private Button SearchByAddbtn;
    @FXML
    private TextField PropertyIDtxt;
    /**
     * Initializes the controller class.
     */
    
     PropertyModel model=new PropertyModel();
     private final ObservableList<Property> PropertyList =
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
    private void CreateProperty() throws IOException {
        App.setRoot("PropertyCreateFXML");
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
 
 public void getPropertiesbyID(int a){
   
   PropertyList.setAll(model.getPropertiesByID(a));
   
   }
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
