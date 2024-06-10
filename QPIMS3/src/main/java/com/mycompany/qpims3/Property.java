/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qpims3;

/**
 *
 * @author tirth
 */
import java.time.LocalDate;

public class Property {

    // Property class and thier associated attributes.
    int id; // Signifies Property ID.
    int streetNumber; // Signifies Property streetNumber.
    String streetName; // Signifies Property streetName.
    String suburb; // Signifies suburb of the Property.

    public enum State {
        // enum variables for states in Australia, this enum variables will be set in the choice box to make it avaliable for user to choose from.
        QLD,
        NSW,
        VIC,
        NT,
        TAS,
        ACT,
        WA
    }
    int bedroomCount;
    int bathroomCount;
    int parkingSpaces;

    public enum PropertyType {
        // enum variables for Types of property, this enum variables will be set in the choice box to make it avaliable for user to choose from.
        House,
        Apartment,
        Unit,
        Land
    }
    String managingAgent; // Signifies the managing agent.
    private LocalDate builtDate; // Signifies the builtDate.
    int customerId; // Signifies the Customer Id associated with property.

    private PropertyType propertyType = PropertyType.House; // initialization of enum object named propertyType
    private State state = State.QLD;// initialization of enum object named state.

    public Property(int id, int streetNumber, String streetName, String suburb, String state, int bedroomCount, int bathroomCount, int parkingSpaces, String propertyType, String managingAgent, String builtDate, int customerId) {
        // Property constructor.
        this.id = id;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.suburb = suburb;
        this.bedroomCount = bedroomCount;
        this.bathroomCount = bathroomCount;
        this.parkingSpaces = parkingSpaces;
        this.propertyType = PropertyType.valueOf(propertyType);
        this.state = State.valueOf(state);
        this.managingAgent = managingAgent;
        this.builtDate = LocalDate.parse(builtDate);
        this.customerId = customerId;
    }
// Below are the required setter and getter methods.

    public int getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String PropertyManager() {
        return managingAgent;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getState() {
        return state.toString();
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public int getBathroomCount() {
        return bathroomCount;
    }

    public String getPropertyType() {
        return propertyType.toString();
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public LocalDate getBuiltDate() {
        return builtDate;
    }

    public int getCustomerID() {
        return customerId;
    }

}
