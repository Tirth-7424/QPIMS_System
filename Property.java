
package com.mycompany.guiprototype;

import java.time.LocalDate;


public class Property {
    int id;
    int streetNumber;
    String streetName;
    String suburb;
    public enum State{
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
    public enum PropertyType{
        House,
        Apartment,
        Unit,
        Land
    }
    String managingAgent;
    private LocalDate builtDate;
    int customerId;
    
    private PropertyType propertyType = PropertyType.House;
    private State state = State.QLD;

    public Property(int id, int streetNumber, String streetName, String suburb, String state,int bedroomCount, int bathroomCount, int parkingSpaces, String propertyType, String managingAgent, String builtDate, int customerId) {
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

    public int getId(){
        return id;
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
    
    

}
