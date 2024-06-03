/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tirth
 */
public class PropertyModel {
      private static final String URL = "jdbc:mysql://localhost/qpims";

   private static final String USERNAME = "root";
   private static final String PASSWORD = "Tirth"; //your own password to Root account of MySQL

   private Connection connection = null; // manages connection
   private PreparedStatement selectAllProperty = null;
   private PreparedStatement selectPropertyByStreetName = null;
   private PreparedStatement selectPropertyByStreetNumber = null;
   private PreparedStatement selectPropertyBySuburb = null;
   private PreparedStatement selectPropertyByState = null;
   private PreparedStatement selectPropertyByID = null;
   private PreparedStatement selectPropertyByType = null;
   private PreparedStatement updateProperty = null;
   private PreparedStatement CreateProperty = null;
   private PreparedStatement CreatePropertyNoId = null;
   CustomerModel cm=new CustomerModel();
   public PropertyModel()
   {
      try
      {
         connection =
            DriverManager.getConnection( URL,USERNAME,PASSWORD );

         // create query that selects all entries in the table
         selectAllProperty =
            connection.prepareStatement( "SELECT * FROM property" );

         // create query that selects entries with a specific name
         selectPropertyByStreetName = connection.prepareStatement(
            "SELECT * FROM property WHERE StreetName = ?" );
         // create query that selects entirs with a specific id
         selectPropertyByStreetNumber = connection.prepareStatement(
            "SELECT * FROM property WHERE StreetNumber = ?" );
         selectPropertyBySuburb = connection.prepareStatement(
            "SELECT * FROM property WHERE Suburb = ?" );
         
         selectPropertyByState = connection.prepareStatement(
            "SELECT * FROM property WHERE State = ?" );
         
         selectPropertyByID = connection.prepareStatement(
            "SELECT * FROM property WHERE ID = ?" );
         
         selectPropertyByType = connection.prepareStatement(
            "SELECT * FROM property WHERE PropertyType = ?" );
         
         
         // create insert that adds a new entry into the database
         CreateProperty = connection.prepareStatement(
            "INSERT INTO property " +
            "( StreetName, StreetNumber, Suburb, State, BuiltYear, Bathrooms, Bedrooms, CarParks, PropertyManager, PropertyType, Customer_CustomerID) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
         // create insert that adds a new entry into the database
         CreatePropertyNoId = connection.prepareStatement(
            "INSERT INTO property " +
            "( StreetName, StreetNumber, Suburb, State, BuiltYear, Bathrooms, Bedrooms, CarParks, PropertyManager, PropertyType, Customer_CustomerID) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
         
         updateProperty = connection.prepareStatement(
        "UPDATE proeprty " +
        "SET StreetName = ?, StreetNumber = ?, Suburb = ?, State = ?, BuiltYear = ?, Bathrooms = ?, Bedrooms = ?, CarParks = ?, PropertyManager = ?, PropertyType = ?, Customer_CustomerID = ?" +
        "WHERE PropertyID = ?" );

      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end Property constructor
   
    public void close()
   {
      try
      {
         connection.close();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
   } // end method close
    
   
    public int addProperty(String StreetName, int StreetNumber, String Suburb, String State, String BuiltYear, int Bathrooms, int Bedrooms, int CarParks, String PropertyManager, String PropertyType, int Customer_CustomerID )
   {
      int result = 0;

      // set parameters, then execute insertNewPatient
      try
      {
         CreateProperty.setString( 1, StreetName );
         CreateProperty.setInt( 2, StreetNumber );
         CreateProperty.setString( 3, Suburb );
         CreateProperty.setString( 4, State );
         CreateProperty.setString( 5, BuiltYear );
         CreateProperty.setInt( 6, Bathrooms );
         CreateProperty.setInt( 7, Bedrooms );
         CreateProperty.setInt( 8, CarParks );
         CreateProperty.setString( 9, PropertyManager );
         CreateProperty.setString( 10, PropertyType);
         CreateProperty.setInt( 11, Customer_CustomerID);
         

         // insert the new entry; returns # of rows updated
         result = CreateProperty.executeUpdate();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
       
      return result;
   } // end method 
    
     public int addPropertyNoId(String StreetName, int StreetNumber, String Suburb, String State, String BuiltYear, int Bathrooms, int Bedrooms, int CarParks, String PropertyManager, String PropertyType )
   {
      int result = 0;

      // set parameters, then execute insertNewPatient
      try
      {
         CreateProperty.setString( 1, StreetName );
         CreateProperty.setInt( 2, StreetNumber );
         CreateProperty.setString( 3, Suburb );
         CreateProperty.setString( 4, State );
         CreateProperty.setString( 5, BuiltYear );
         CreateProperty.setInt( 6, Bathrooms );
         CreateProperty.setInt( 7, Bedrooms );
         CreateProperty.setInt( 8, CarParks );
         CreateProperty.setString( 9, PropertyManager );
         CreateProperty.setString( 10, PropertyType);
         CreateProperty.setNull( 11, 0);

         // insert the new entry; returns # of rows updated
         result = CreateProperty.executeUpdate();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   } // end method 
    
    
    
    public List< Property > getPropertiesByID( int id )
   {
      List< Property > results = null;
      ResultSet resultSet = null;

      try
      {
         selectPropertyByID.setInt( 1, id ); // specify id

         // executeQuery returns ResultSet containing matching entries
         resultSet = selectPropertyByID.executeQuery();

         results = new ArrayList< Property >();

         while ( resultSet.next() )
         {
          results.add( new Property(
               resultSet.getInt( "Id" ),
               resultSet.getInt( "streetNumber" ),
               resultSet.getString( "streetName" ),
               resultSet.getString( "Suburb" ),
               resultSet.getString( "state" ),
               resultSet.getInt( "bedroomCount" ),
               resultSet.getInt( "bathroomCount" ),
               resultSet.getInt( "parkingSpaces" ),
               resultSet.getString( "PropertyType" ),
               resultSet.getString( "ManagingAgent" ),
               resultSet.getString( "builtDate" ),
               resultSet.getInt( "CustomerId" )
                ) );
         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
      finally
      {
         try
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();
            close();
         } // end catch
      } // end finally

      return results;
   } // end method getByName
   // add an entry
    
   public boolean findCustomer(int CID){
   List< Customer > results = null;
   boolean customerfound = false;
   results = cm.getAllCustomers();
   
   for(int i = 0; i < results.size(); i++ ){
   if(CID == results.get(i).getCustomerID()){
    customerfound = true;
   }
   
   }
   return customerfound;
   }
   
   
}
