/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qpims3;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class CustomerFXMLController implements Initializable {

    @FXML
    private Button Searchbtn;
    @FXML
    private Button SearchLastNameID;
    @FXML
    private Button SearchIDbtn;
    @FXML
    private Button addcustomerbtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button backbtn;
    @FXML
    private TextField Lnametxt;
    @FXML
    private TextField Pnumbertxt;
    @FXML
    private TextField CIDtxt;
    @FXML
    private AnchorPane customerSearchPane;
    @FXML
    private AnchorPane updateCustomerPane;
    @FXML
    private Button Cancelbutton;
    @FXML
    private TextField Fnameupdatetxt;
    @FXML
    private TextField Lnameupdatetxt;
    @FXML
    private TextField Pnumberupdatetxt;
    @FXML
    private TextField Addressupdatetxt;
    @FXML
    private Button UpdateCustomer;
    @FXML
    private ListView<Customer> listview;

    /**
     * Initializes the controller class.
     */
    CustomerModel model=new CustomerModel();//initilaize- important
    List< Customer > results;
    int numberOfEntries;
    private final ObservableList<Customer> CustomerList =
	      FXCollections.observableArrayList();
    Customer updateCustomer;
    private Customer customer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerSearchPane.setVisible(true);
        updateCustomerPane.setVisible(false);
        
    } 
    @FXML
    private void Customer2switch(ActionEvent event) throws IOException {
        updatebtn.setDisable(true);
       App.setRoot("CustomerCreateFXML");
}
    @FXML
      private void buttonFindPhoneHandler(ActionEvent event){
        Lnametxt.clear();
        CIDtxt.clear();
        updatebtn.setDisable(true);
        String a = Pnumbertxt.getText();
        if(a.isEmpty() || a.equals("")){
        displayMessage("Please Enter the Phone Number!!");
        }
        else{
      if(isInteger(Pnumbertxt.getText()) == true){
        int foundPnumber = Integer.parseInt(Pnumbertxt.getText());
        results = model.getCustomersByPhone(foundPnumber);

        numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
       listview.setItems(CustomerList); // bind to contactsList  
       listview.setCellFactory(param -> new CustomerListID());
       getCustomersbyPhone(foundPnumber);   
       listview.getSelectionModel().selectedItemProperty().addListener(
        (var observableValue, var oldValue, var newValue) -> {
        if (newValue != null) {
            
            updateCustomer = newValue;
            setfields(updateCustomer);
            updatebtn.setDisable(false);
              
    }
        }
       );
      }
      else{
        displayMessage("Not found");
        Pnumbertxt.clear();
        CustomerList.clear();
      }
      }
      else{
      displayMessage("Only Integer values accepted for phone number.");
      Pnumbertxt.clear();
      CustomerList.clear();
      }
    }
        
 
      }
      
    
    @FXML
      private void buttonFindIDHandler(ActionEvent event){
       Lnametxt.clear();
       Pnumbertxt.clear();
       updatebtn.setDisable(true);
      
        String foundID= CIDtxt.getText();
        
        if(foundID.isEmpty()){
        displayMessage("Please Enter a ID!!");
        }
        else{
            
     if(isInteger(foundID)){
       int id = Integer.parseInt(foundID);
        results = model.getCustomersByID(id);

        numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
       listview.setItems(CustomerList); // bind to contactsList
       listview.setCellFactory(param -> new CustomerListID());   
       getCustomersbyID(id);   
       listview.getSelectionModel().selectedItemProperty().addListener(
        (var observableValue, var oldValue, var newValue) -> {
        if (newValue != null) {
            
            updateCustomer = newValue;
            setfields(updateCustomer);
            updatebtn.setDisable(false);
              
    }
        }
       );
      }
      else{
        displayMessage("Not found");
        CIDtxt.clear();
        clearfields();
        
      }
     }
     else{
     displayMessage("ID should be Integer.");
     }
    }
      }
      
      
    @FXML
    private void buttonFindNameHandler(ActionEvent event) {
       CIDtxt.clear();
       Pnumbertxt.clear();
       updatebtn.setDisable(true); 
      String foundName=Lnametxt.getText();
         
       if(foundName.isEmpty()) {
       displayMessage("Please Enter a Last Name!!");
       }
       
       else{

        results = model.getCustomersByName(foundName);

        numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
       listview.setItems(CustomerList); // bind to contactsList
       listview.setCellFactory(param -> new CustomerListID());   
       getCustomersbyName(foundName);
       listview.getSelectionModel().selectedItemProperty().addListener(
        (var observableValue, var oldValue, var newValue) -> {
        if (newValue != null) {
            
            updateCustomer = newValue;
            setfields(updateCustomer);
            updatebtn.setDisable(false);
              
    }
        }
       );
       
      }
      else{
        displayMessage("Not found");
      clearfields();
      }
       }
      
      
      
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
         
    @FXML
    private void updateCustomerSwitch(ActionEvent event) throws IOException {
        customerSearchPane.setVisible(false);
        updateCustomerPane.setVisible(true);
        
        
    }
    
    @FXML
    private void updateCustomer(ActionEvent event){
    
        try{
            
        int CID = updateCustomer.getCustomerID();
        String Fname = Fnameupdatetxt.getText();
        String Lname = Lnameupdatetxt.getText();
        String Address = Addressupdatetxt.getText();
        int Phnumber = Integer.parseInt(Pnumberupdatetxt.getText());

        model.updateCustomer(CID, Fname, Lname, Address, Phnumber);
        displayMessage("Customer Update successful!!");
        clearfieldsupdate();
        }catch (NumberFormatException e) {
        // Handle the case where parsing fails (e.g., invalid integer input)
        displayMessage("Invalid input for CID or Phone Number. Please enter valid numbers.");
    } catch (Exception e) {
        // Handle other exceptions (e.g., database errors)
        displayMessage("Error updating customer: " + e.getMessage());
        e.printStackTrace(); // Print stack trace for debugging
    }
    
    }
    
     @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("MainMenuFXML");     
    }
      
    public void clearfields(){
        
        Lnametxt.clear();
        Pnumbertxt.clear();
        CustomerList.clear();
   
   }
        public void clearfieldsupdate(){
        Fnameupdatetxt.clear();
        Lnameupdatetxt.clear();
        Pnumberupdatetxt.clear();
        Addressupdatetxt.clear();
   
   }
    
    public void getCustomersbyPhone(int a){
   
   CustomerList.setAll(model.getCustomersByPhone(a));
   
   }
    
   public void getCustomersbyID(int a){
   
   CustomerList.setAll(model.getCustomersByID(a));
   
   }
   public void getCustomersbyName(String a){
   
   CustomerList.setAll(model.getCustomersByName(a));
   if( CustomerList.isEmpty()){
   CustomerList.setAll(model.getCustomersByName(a.toLowerCase()));
   }
   if( CustomerList.isEmpty()){
   CustomerList.setAll(model.getCustomersByName(a.toUpperCase()));
   }
   
   }
   
   public void setfields(Customer newValue){
          Fnameupdatetxt.setText(newValue.getFname());
          Addressupdatetxt.setText(newValue.getAddress());
          Lnameupdatetxt.setText(newValue.getLname());
          Pnumberupdatetxt.setText("" + newValue.getPhoneNumber());
            System.out.println( newValue.getCustomerID() +"----");
            System.out.println( newValue.getLname() +"----");
            System.out.println( newValue.getAddress()+"----");
   } 
    
   private static class CustomerListID extends ListCell<Customer> {
        @Override
        protected void updateItem(Customer customer, boolean empty) {
            super.updateItem(customer, empty);
            if (empty || customer == null) {
                setText(null);
            } else {
                setText(customer.getCustomerID() + " " + customer.getLname() + " " + customer.getFname());
            }
        }
   }
    
}
