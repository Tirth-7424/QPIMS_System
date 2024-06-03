/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;

/**
 *
 * @author tirth
 */
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
   private PreparedStatement selectAllCustomers = null;
   private PreparedStatement selectCustomerByName = null;
   private PreparedStatement selectCustomerByPhone = null;
   private PreparedStatement selectCustomerByID = null;
   private PreparedStatement insertNewCustomer = null;
   private PreparedStatement updateCustomer = null;
   private PreparedStatement getUser = null;
   private PreparedStatement insertUser = null;
   
   // constructor
   public CustomerModel()
   {
      try
      {
         connection =
            DriverManager.getConnection( URL,USERNAME,PASSWORD );

         // create query that selects all entries in the table
         selectAllCustomers =
            connection.prepareStatement( "SELECT * FROM customer" );

         // create query that selects entries with a specific name
         selectCustomerByName = connection.prepareStatement(
            "SELECT * FROM customer WHERE Lname = ?" );
         // create query that selects entirs with a specific id
         selectCustomerByID = connection.prepareStatement(
            "SELECT * FROM customer WHERE CustomerID = ?" );
         selectCustomerByPhone = connection.prepareStatement(
            "SELECT * FROM customer WHERE PhoneNumber = ?" );
         // create insert that adds a new entry into the database
         insertNewCustomer = connection.prepareStatement(
            "INSERT INTO customer " +
            "( Fname, Lname, Address, PhoneNumber) " +
            "VALUES ( ?, ?, ?, ? )" );
         updateCustomer = connection.prepareStatement(
        "UPDATE customer " +
        "SET Fname = ?, Lname = ?, Address = ?, PhoneNumber = ? " +
        "WHERE CustomerID = ?" );
        insertUser = connection.prepareStatement("insert into userlogin"
                    + "(LoginID, LoginPassword, Email, Fname, Lname)"
                    + "values (?,?,?,?,?)");
        getUser = connection.prepareStatement("select * from userlogin where LoginID=? and LoginPassword=?");

      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end PatientModel constructor
   
   public String generateHash(String password)
    {
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
	        }
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
    }//end
   
   
   public  int addUsers(String LoginID, String LoginPassword, String Email, String Fname, String Lname)  {
	int result=0;

        if(LoginPassword.equals(""))
        {
            result=0;
            return result;
        }

        String hashedPassword="";
        hashedPassword=generateHash(LoginPassword);

        try {

                insertUser.setString(1, LoginID);
                insertUser.setString(2, hashedPassword);
                insertUser.setString(3, Email);
                insertUser.setString(4, Fname);
                insertUser.setString(5, Lname);
                result=insertUser.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }

        return result;
    }
   
   public boolean checkLogin(String u, String p)
    {
      boolean b=false;
      ResultSet resultSet = null;
      String hashedPassword="";
      hashedPassword=generateHash(p);
        System.out.println(hashedPassword);
      try
      {
          getUser.setString(1,u);
          getUser.setString(2,hashedPassword);
         resultSet = getUser.executeQuery();

         while ( resultSet.next() )
         {
            String user=resultSet.getString(1);
            String password=resultSet.getString(2);

            if(user.equals(u) && password.equals(hashedPassword))
                b=true;

         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
        return b;
    }
   
    public List< Customer > getAllCustomers()
   {
      List< Customer > results = new ArrayList< Customer >();
      ResultSet resultSet = null;

      try
      {
         // executeQuery returns ResultSet containing matching entries
         resultSet = selectAllCustomers.executeQuery();
         results = new ArrayList< Customer>();

         while ( resultSet.next() )
         {
            results.add( new Customer(
               resultSet.getInt( "CustomerID" ),
               resultSet.getString( "FName" ),
               resultSet.getString( "LName" ),
               resultSet.getString( "Address" ),
               resultSet.getInt( "PhoneNumber" )
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
   } // end method getAllPatients

   // select patient by name
    
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
    
    public List< Customer > getCustomersByID( int id )
   {
      List< Customer > results = null;
      ResultSet resultSet = null;

      try
      {
         selectCustomerByID.setInt( 1, id ); // specify id

         // executeQuery returns ResultSet containing matching entries
         resultSet = selectCustomerByID.executeQuery();

         results = new ArrayList< Customer >();

         while ( resultSet.next() )
         {
          results.add( new Customer(
               resultSet.getInt( "CustomerID" ),
               resultSet.getString( "FName" ),
               resultSet.getString( "LName" ),
               resultSet.getString( "Address" ),
               resultSet.getInt( "PhoneNumber" )
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
    
    public List< Customer > getCustomersByName( String name )
   {
      List< Customer > results = null;
      ResultSet resultSet = null;

      try
      {
         selectCustomerByName.setString( 1, name ); // specify last name

         // executeQuery returns ResultSet containing matching entries
         resultSet = selectCustomerByName.executeQuery();

         results = new ArrayList< Customer >();

         while ( resultSet.next() )
         {
            results.add( new Customer(
               resultSet.getInt( "CustomerID" ),
               resultSet.getString( "FName" ),
               resultSet.getString( "LName" ),
               resultSet.getString( "Address" ),
               resultSet.getInt( "PhoneNumber" )
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
    
    public List< Customer > getCustomersByPhone( int phone )
   {
      List< Customer > results = null;
      ResultSet resultSet = null;

      try
      {
         selectCustomerByPhone.setInt( 1, phone );

         // executeQuery returns ResultSet containing matching entries
         resultSet = selectCustomerByPhone.executeQuery();

         results = new ArrayList< Customer >();

         while ( resultSet.next() )
         {
            results.add( new Customer(
               resultSet.getInt( "CustomerID" ),
               resultSet.getString( "FName" ),
               resultSet.getString( "LName" ),
               resultSet.getString( "Address" ),
               resultSet.getInt( "PhoneNumber" )
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
    
    
    
    public List< Customer > getallCustomers( )
   {
      List< Customer > results = null;
      ResultSet resultSet = null;

      try
      {
        

         // executeQuery returns ResultSet containing matching entries
         resultSet = selectAllCustomers.executeQuery();

         results = new ArrayList< Customer >();

         while ( resultSet.next() )
         {
            results.add( new Customer(
               resultSet.getInt( "CustomerID" ),
               resultSet.getString( "FName" ),
               resultSet.getString( "LName" ),
               resultSet.getString( "Address" ),
               resultSet.getInt( "PhoneNumber" )
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
    
    public int addCustomer(String Fname, String Lname, String Address, int Phnumber )
   {
      int result = 0;

      // set parameters, then execute insertNewPatient
      try
      {
         insertNewCustomer.setString( 1, Fname );
         insertNewCustomer.setString( 2, Lname );
         insertNewCustomer.setString( 3, Address );
         insertNewCustomer.setInt( 4, Phnumber );

         // insert the new entry; returns # of rows updated
         result = insertNewCustomer.executeUpdate();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch

      return result;
   } // end method addPatient
    
    public int updateCustomer(
     int CID, String Fname, String Lname, String Address,int Phnumber )
   {
      int result = 0;

      // set parameters, then execute insertNewPatient
      try
      {
         updateCustomer.setInt(5,CID );
         updateCustomer.setString( 1, Fname );
         updateCustomer.setString( 2, Lname );
         updateCustomer.setString( 3, Address );
         updateCustomer.setInt( 4, Phnumber );

         // insert the new entry; returns # of rows updated
         result = updateCustomer.executeUpdate();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch

      return result;
   } // end method addPatient

}
