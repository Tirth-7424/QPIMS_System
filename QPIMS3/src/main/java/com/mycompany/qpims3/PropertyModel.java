/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;
// Below are the imports help in fetching library and thier elements to be used in application.

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

    private static final String URL = "jdbc:mysql://localhost/qpims"; //Link to database server.

    private static final String USERNAME = "root"; // username of database server.
    private static final String PASSWORD = "Tirth"; // password of database server.

    private Connection connection = null; // manages connection
    private PreparedStatement selectAllProperty = null; // Connection statement to selectAllProperty.
    private PreparedStatement selectPropertyByStreetName = null; //Connection statement to select property by street name.
    private PreparedStatement selectPropertyByStreetNumber = null; //Connection statement to select property by Street number.
    private PreparedStatement selectPropertyBySuburb = null; // Connection statement to select property by suburb.
    private PreparedStatement selectPropertyByState = null; // Connection statement to select property by state.
    private PreparedStatement selectPropertyByID = null; // Connection statement to select property by ID. 
    private PreparedStatement selectPropertyByType = null; // Connection statement to select property by Type.
    private PreparedStatement updateProperty = null; // Connection statement to update property.
    private PreparedStatement updatePropertyNoId = null; // Connection statement to update property with no Id.
    private PreparedStatement CreateProperty = null; // Connection statement to create property with customer Id.
    private PreparedStatement CreatePropertyNoId = null; // Connection statement to Create property with no customer associated.
    CustomerModel cm = new CustomerModel(); // initializing instance of customer model.

    public PropertyModel() {
        try {
            connection
                    = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // create query that selects all entries in the table
            selectAllProperty
                    = connection.prepareStatement("SELECT * FROM property");

            // create query that selects entries with a specific name
            selectPropertyByStreetName = connection.prepareStatement(
                    "SELECT * FROM property WHERE StreetName = ?");
            // create query that selects entirs with a specific street number
            selectPropertyByStreetNumber = connection.prepareStatement(
                    "SELECT * FROM property WHERE StreetNumber = ?");
            // create query that selects entirs with a specific suburb 
            selectPropertyBySuburb = connection.prepareStatement(
                    "SELECT * FROM property WHERE Suburb = ?");
            // create query that selects entirs with a specific state
            selectPropertyByState = connection.prepareStatement(
                    "SELECT * FROM property WHERE State = ?");
            // create query that selects entirs with a specific ID
            selectPropertyByID = connection.prepareStatement(
                    "SELECT * FROM property WHERE PropertyID = ?");
            // create query that selects entirs with a specific type
            selectPropertyByType = connection.prepareStatement(
                    "SELECT * FROM property WHERE PropertyType = ?");

            // create insert that adds a new entry into the database
            CreateProperty = connection.prepareStatement(
                    "INSERT INTO property "
                    + "( StreetName, StreetNumber, Suburb, State, BuiltYear, Bathrooms, Bedrooms, CarParks, PropertyManager, PropertyType, Customer_CustomerID) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            // create insert that adds a new entry into the database
            CreatePropertyNoId = connection.prepareStatement(
                    "INSERT INTO property "
                    + "( StreetName, StreetNumber, Suburb, State, BuiltYear, Bathrooms, Bedrooms, CarParks, PropertyManager, PropertyType, Customer_CustomerID) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

            // create update that updates the existing property entry.
            updateProperty = connection.prepareStatement(
                    "UPDATE property "
                    + "SET StreetName = ?, StreetNumber = ?, Suburb = ?, State = ?, BuiltYear = ?, Bathrooms = ?, Bedrooms = ?, CarParks = ?, PropertyManager = ?, PropertyType = ?, Customer_CustomerID = ? "
                    + // Notice the space before WHERE
                    "WHERE PropertyID = ?");
            // create update that updates the existing property entry.
            updatePropertyNoId = connection.prepareStatement(
                    "UPDATE property "
                    + "SET StreetName = ?, StreetNumber = ?, Suburb = ?, State = ?, BuiltYear = ?, Bathrooms = ?, Bedrooms = ?, CarParks = ?, PropertyManager = ?, PropertyType = ?, Customer_CustomerID = ? "
                    + // Notice the space before WHERE
                    "WHERE PropertyID = ?");

        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    } // end Property constructor

    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close

    public int addProperty(String StreetName, int StreetNumber, String Suburb, String State, String BuiltYear, int Bathrooms, int Bedrooms, int CarParks, String PropertyManager, String PropertyType, int Customer_CustomerID) {
        int result = 0;

        // set parameters, then execute CreateProperty
        try {
            CreateProperty.setString(1, StreetName);// Passing first parameter as StreetName to the executing statement.
            CreateProperty.setInt(2, StreetNumber);// Passing second parameter as StreetNumber to the executing statement.
            CreateProperty.setString(3, Suburb);// Passing third parameter as Suburb to the executing statement.
            CreateProperty.setString(4, State);// Passing fourth parameter as state to the executing statement.
            CreateProperty.setString(5, BuiltYear);// Passing fifth parameter as Builtyear to the executing statement.
            CreateProperty.setInt(6, Bathrooms);// Passing sixth parameter as Bathrooms to the executing statement.
            CreateProperty.setInt(7, Bedrooms);// Passing seventh parameter as bedrooms to the executing statement.
            CreateProperty.setInt(8, CarParks);// Passing eighth parameter as CarParks to the executing statement.
            CreateProperty.setString(9, PropertyManager);// Passing ninth parameter as Propertymanager to the executing statement.
            CreateProperty.setString(10, PropertyType);// Passing tenth parameter as PropertyType to the executing statement.
            CreateProperty.setInt(11, Customer_CustomerID);// Passing eleventh parameter as Customer ID to the executing statement.

            // insert the new entry; returns # of rows updated
            result = CreateProperty.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method 

    public int addPropertyNoId(String StreetName, int StreetNumber, String Suburb, String State, String BuiltYear, int Bathrooms, int Bedrooms, int CarParks, String PropertyManager, String PropertyType) {
        int result = 0;

        // set parameters, then execute CreateProperty
        try {
            CreateProperty.setString(1, StreetName);
            CreateProperty.setInt(2, StreetNumber);
            CreateProperty.setString(3, Suburb);
            CreateProperty.setString(4, State);
            CreateProperty.setString(5, BuiltYear);
            CreateProperty.setInt(6, Bathrooms);
            CreateProperty.setInt(7, Bedrooms);
            CreateProperty.setInt(8, CarParks);
            CreateProperty.setString(9, PropertyManager);
            CreateProperty.setString(10, PropertyType);
            CreateProperty.setNull(11, 0);

            // insert the new entry; returns # of rows updated
            result = CreateProperty.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method 

    public List< Property> getPropertiesByID(int id) {
        List< Property> results = null;
        ResultSet resultSet = null;

        try {
            selectPropertyByID.setInt(1, id); // specify id

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectPropertyByID.executeQuery();

            results = new ArrayList< Property>();

            while (resultSet.next()) {
                results.add(new Property(
                        resultSet.getInt("PropertyID"),
                        resultSet.getInt("StreetNumber"),
                        resultSet.getString("StreetName"),
                        resultSet.getString("Suburb"),
                        resultSet.getString("State"),
                        resultSet.getInt("Bedrooms"),
                        resultSet.getInt("Bathrooms"),
                        resultSet.getInt("CarParks"),
                        resultSet.getString("PropertyType"),
                        resultSet.getString("PropertyManager"),
                        resultSet.getString("BuiltYear"),
                        resultSet.getInt("Customer_CustomerID")
                ));
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    } // end method

    public int updateProperty(int PropertyId, String StreetName, int StreetNumber, String Suburb, String State, String BuiltYear, int Bathrooms, int Bedrooms, int CarParks, String PropertyManager, String PropertyType, int Customer_CustomerID) {
        int result = 0;

        // set parameters, then execute updateProperty
        try {
            updateProperty.setInt(12, PropertyId);
            updateProperty.setString(1, StreetName);
            updateProperty.setInt(2, StreetNumber);
            updateProperty.setString(3, Suburb);
            updateProperty.setString(4, State);
            updateProperty.setString(5, BuiltYear);
            updateProperty.setInt(6, Bathrooms);
            updateProperty.setInt(7, Bedrooms);
            updateProperty.setInt(8, CarParks);
            updateProperty.setString(9, PropertyManager);
            updateProperty.setString(10, PropertyType);
            updateProperty.setInt(11, Customer_CustomerID);

            // insert the new entry; returns # of rows updated
            result = updateProperty.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method

    public int updatePropertyNoId(int PropertyId, String StreetName, int StreetNumber, String Suburb, String State, String BuiltYear, int Bathrooms, int Bedrooms, int CarParks, String PropertyManager, String PropertyType) {
        int result = 0;

        // set parameters, then execute updatePropertyNoId
        try {
            updatePropertyNoId.setString(1, StreetName);
            updatePropertyNoId.setInt(2, StreetNumber);
            updatePropertyNoId.setString(3, Suburb);
            updatePropertyNoId.setString(4, State);
            updatePropertyNoId.setString(5, BuiltYear);
            updatePropertyNoId.setInt(6, Bathrooms);
            updatePropertyNoId.setInt(7, Bedrooms);
            updatePropertyNoId.setInt(8, CarParks);
            updatePropertyNoId.setString(9, PropertyManager);
            updatePropertyNoId.setString(10, PropertyType);
            updatePropertyNoId.setNull(11, java.sql.Types.INTEGER); // Assuming Customer_CustomerID is an integer
            updatePropertyNoId.setInt(12, PropertyId);

            // insert the new entry; returns # of rows updated
            result = updatePropertyNoId.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method 

    public List< Property> searchProperties(String StreetName) {
        List< Property> results = new ArrayList< Property>();
        ResultSet resultSet = null;

        try {
            selectPropertyByStreetName.setString(1, StreetName);
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectPropertyByStreetName.executeQuery();
            results = new ArrayList< Property>();

            while (resultSet.next()) {
                results.add(new Property(// Adding the obtained matching data from ResultSet to result list.
                        resultSet.getInt("PropertyID"),
                        resultSet.getInt("StreetNumber"),
                        resultSet.getString("StreetName"),
                        resultSet.getString("Suburb"),
                        resultSet.getString("State"),
                        resultSet.getInt("Bedrooms"),
                        resultSet.getInt("Bathrooms"),
                        resultSet.getInt("CarParks"),
                        resultSet.getString("PropertyType"),
                        resultSet.getString("PropertyManager"),
                        resultSet.getString("BuiltYear"),
                        resultSet.getInt("Customer_CustomerID")
                ));
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        }

        return results;
    }// end finally

    public List< Property> getAllProperties() {
        List< Property> results = new ArrayList< Property>();
        ResultSet resultSet = null;

        try {
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllProperty.executeQuery();
            results = new ArrayList< Property>();

            while (resultSet.next()) {
                results.add(new Property( // Adding the matching data obtained from Result to result list.
                        resultSet.getInt("PropertyID"),
                        resultSet.getInt("StreetNumber"),
                        resultSet.getString("StreetName"),
                        resultSet.getString("Suburb"),
                        resultSet.getString("State"),
                        resultSet.getInt("Bedrooms"),
                        resultSet.getInt("Bathrooms"),
                        resultSet.getInt("CarParks"),
                        resultSet.getString("PropertyType"),
                        resultSet.getString("PropertyManager"),
                        resultSet.getString("BuiltYear"),
                        resultSet.getInt("Customer_CustomerID")
                ));
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally
        return results;
    }

    public boolean findCustomer(int CID) {
        // Function to find if the required customer exist on the basis of unique ID.
        List< Customer> results = null;
        boolean customerfound = false;
        results = cm.getAllCustomers();

        for (int i = 0; i < results.size(); i++) {
            if (CID == results.get(i).getCustomerID()) {
                customerfound = true;
            }

        }
        return customerfound;
    }

}
