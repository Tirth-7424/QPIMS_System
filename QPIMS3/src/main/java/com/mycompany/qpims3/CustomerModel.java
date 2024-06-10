/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;

/**
 *
 * @author tirth
 */
// Below are the imports help in fetching library and thier elements to be used in application.
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class CustomerModel {

    private static final String URL = "jdbc:mysql://localhost/qpims";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "Tirth"; //your own password to Root account of MySQL

    private Connection connection = null; // manages connection
    private PreparedStatement selectAllCustomers = null; // PreparedStatement for selecting all the customers.
    private PreparedStatement selectCustomerByName = null; // PreparedStatement for selecting customers by name.
    private PreparedStatement selectCustomerByPhone = null; // PreparedStatement for selecting customers by phone number.
    private PreparedStatement selectCustomerByID = null; // PreparedStatement for selecting customers by customer ID.
    private PreparedStatement insertNewCustomer = null; // PreparedStatement for inserting new customer.
    private PreparedStatement updateCustomer = null; // PreparedStatement for updating customer.
    private PreparedStatement getUser = null; // PreparedStatement for fetching a valid user.
    private PreparedStatement insertUser = null; // PreparedStatement for insertin/Registering a new user.

    // constructor
    public CustomerModel() {
        try {
            connection
                    = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // create query that selects all entries in the table
            selectAllCustomers
                    = connection.prepareStatement("SELECT * FROM customer");

            // create query that selects entries with a specific name
            selectCustomerByName = connection.prepareStatement(
                    "SELECT * FROM customer WHERE Lname = ?");
            // create query that selects entirs with a specific id
            selectCustomerByID = connection.prepareStatement(
                    "SELECT * FROM customer WHERE CustomerID = ?");
            // create query that selects entirs with a specific phone number
            selectCustomerByPhone = connection.prepareStatement(
                    "SELECT * FROM customer WHERE PhoneNumber = ?");
            // create insert that adds a new entry into the database
            insertNewCustomer = connection.prepareStatement(
                    "INSERT INTO customer "
                    + "( Fname, Lname, Address, PhoneNumber) "
                    + "VALUES ( ?, ?, ?, ? )");
            // create query to update a customer
            updateCustomer = connection.prepareStatement(
                    "UPDATE customer "
                    + "SET Fname = ?, Lname = ?, Address = ?, PhoneNumber = ? "
                    + "WHERE CustomerID = ?");
            // create query to insert a user.
            insertUser = connection.prepareStatement("insert into userlogin"
                    + "(LoginID, LoginPassword, Email, Fname, Lname)"
                    + "values (?,?,?,?,?)");
            getUser = connection.prepareStatement("select * from userlogin where LoginID=? and LoginPassword=?");

        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    } // end 

    public String generateHash(String password) {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(password.getBytes());

            // Convert byte array into signum representation
            BigInteger signum = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = signum.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }//end

    public int addUsers(String LoginID, String LoginPassword, String Email, String Fname, String Lname) { //Function to add a user.
        int result = 0;

        if (LoginPassword.equals("")) {
            // If the login password is equal to null, then return 0.
            result = 0;
            return result;
        }

        String hashedPassword = "";
        hashedPassword = generateHash(LoginPassword); // This line of code will generate the hashPassword.

        try {

            insertUser.setString(1, LoginID); // Declaring/setting the parameters for connection statements.
            insertUser.setString(2, hashedPassword);// Declaring/setting the parameters for connection statement.
            insertUser.setString(3, Email);// Declaring/setting the parameters for connection statement.
            insertUser.setString(4, Fname);// Declaring/setting the parameters for connection statement.
            insertUser.setString(5, Lname);// Declaring/setting the parameters for connection statement.
            result = insertUser.executeUpdate();// executing the insertuser statement with declared parameters. 

        } catch (SQLException e) {

            // If the program fails in addinga user. 
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }

        return result;
    }

    public boolean checkLogin(String u, String p) // Checking login with two parameters i.e. Password and username.
    {
        boolean b = false;
        ResultSet resultSet = null;
        String hashedPassword = "";
        hashedPassword = generateHash(p);
        System.out.println(hashedPassword);
        try {
            //setting the parameter for the connection statement
            getUser.setString(1, u);
            getUser.setString(2, hashedPassword);
            resultSet = getUser.executeQuery(); //executing the connection statement

            while (resultSet.next()) {
                // while statement to go through loop if there are more than one matching parameters. 
                String user = resultSet.getString(1);
                String password = resultSet.getString(2);

                if (user.equals(u) && password.equals(hashedPassword)) // if condition statement for matching the exact LoginId and password.
                {
                    b = true; // returns true if the password and Loginid matches.
                }
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        return b;
    }

    public List< Customer> getAllCustomers() {
        List< Customer> results = new ArrayList< Customer>();
        ResultSet resultSet = null;

        try {
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllCustomers.executeQuery();
            results = new ArrayList< Customer>();

            while (resultSet.next()) {
                results.add(new Customer( // adding the matching result from ResultSet to result.
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("FName"),
                        resultSet.getString("LName"),
                        resultSet.getString("Address"),
                        resultSet.getInt("PhoneNumber")
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

    public void close() { // Function to close the connection, hence there will be no further operations if there is any error.
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close

    public List< Customer> getCustomersByID(int id) {
        List< Customer> results = null;
        ResultSet resultSet = null;

        try {
            selectCustomerByID.setInt(1, id); // specify id

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectCustomerByID.executeQuery();

            results = new ArrayList< Customer>();

            while (resultSet.next()) {
                results.add(new Customer( // Adding matching entreis from ResultSet to results. 
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("FName"),
                        resultSet.getString("LName"),
                        resultSet.getString("Address"),
                        resultSet.getInt("PhoneNumber")
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
    // add an entry

    public List< Customer> getCustomersByName(String name) {
        List< Customer> results = null;
        ResultSet resultSet = null;

        try {
            selectCustomerByName.setString(1, name); // specify last name

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectCustomerByName.executeQuery();

            results = new ArrayList< Customer>();

            while (resultSet.next()) {
                results.add(new Customer( // adding matching entries from ResultSet to result.
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("FName"),
                        resultSet.getString("LName"),
                        resultSet.getString("Address"),
                        resultSet.getInt("PhoneNumber")
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

    public List< Customer> getCustomersByPhone(int phone) {
        List< Customer> results = null;
        ResultSet resultSet = null;

        try {
            selectCustomerByPhone.setInt(1, phone); //specifiing the phone number as the parameter.

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectCustomerByPhone.executeQuery();

            results = new ArrayList< Customer>();

            while (resultSet.next()) {
                results.add(new Customer( //Adding all the matching entries from ResultSet to result.
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("FName"),
                        resultSet.getString("LName"),
                        resultSet.getString("Address"),
                        resultSet.getInt("PhoneNumber")
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

    public List< Customer> getallCustomers() {
        List< Customer> results = null;
        ResultSet resultSet = null;

        try {

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllCustomers.executeQuery();

            results = new ArrayList< Customer>();

            while (resultSet.next()) {
                results.add(new Customer( // Adding all the matching entries from ResultSet to result. 
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("FName"),
                        resultSet.getString("LName"),
                        resultSet.getString("Address"),
                        resultSet.getInt("PhoneNumber")
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

    public int addCustomer(String Fname, String Lname, String Address, int Phnumber) {
        int result = 0;

        // set parameters, then execute the connection statement that will fetch the matching data from SQL database server. 
        try {
            insertNewCustomer.setString(1, Fname); // Passing Fname as the argument.
            insertNewCustomer.setString(2, Lname); // Passing Lname as the argument.
            insertNewCustomer.setString(3, Address); // Passing Address as the argument.
            insertNewCustomer.setInt(4, Phnumber); // Passing Phnumber as the argument.

            // the query is executed and all the matching result is added to result List. 
            result = insertNewCustomer.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method

    public int updateCustomer(
            int CID, String Fname, String Lname, String Address, int Phnumber) {
        int result = 0;

        // set parameters, then execute updateCustomer.
        try {
            updateCustomer.setInt(5, CID);
            updateCustomer.setString(1, Fname);
            updateCustomer.setString(2, Lname);
            updateCustomer.setString(3, Address);
            updateCustomer.setInt(4, Phnumber);

            // insert the new entry; returns # of rows updated
            result = updateCustomer.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    } // end method

}
