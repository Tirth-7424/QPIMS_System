/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;

/**
 *
 * @author tirth
 */
public class Customer {

    // Customer class will contain following fields:
    private int CustomerID;
    private String Fname;
    private String Lname;
    private String Address;
    private int PhoneNumber;

    public Customer() {
        // Empty constructor
    }

    public Customer(int CustomerID, String Fname, String Lname, String Address, int PhoneNumber) {
        // Constructor
        this.CustomerID = CustomerID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }

    // Below are the setter and getter methods.
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPhoneNumber(int PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getAddress() {
        return Address;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public String toStringname() {
        return Fname; // Display customer name in ComboBox
    }

}
