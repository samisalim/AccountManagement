/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useraccounts;

/**
 *
 * @author Salim
 */
// Owner mailing address.
public class Address {

    private String street;

    private String city;

    private String state;

    private String zip;

    public Owner myOwner;

    public Address(String street, String city, String state, String zip) {
        if (street == null || street.length() == 0 || city == null || city.length() == 0 || state == null || state.length() == 0 || zip == null || zip.length() == 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        /*
       * Validate parameters
       * Assign parameters's values to attributes 
         */
    }

    private Address(String line) {
        street = line.substring(line.indexOf("<street>") + 8, line.indexOf("</street>"));
        city = line.substring(line.indexOf("<city>") + 6, line.indexOf("</city>"));
        state = line.substring(line.indexOf("<state>") + 7, line.indexOf("</state>"));
        zip = line.substring(line.indexOf("<zip>") + 5, line.indexOf("</zip>"));
        if (street == null || street.length() == 0 || city == null || city.length() == 0 || state == null || state.length() == 0 || zip == null || zip.length() == 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
    }
    // Get the street from the address

    public String getStreet() {
        return street;
    }
    // Get the city from the address

    public String getCity() {
        return city;
    }
    // Get the state from the address

    public String getState() {
        return state;
    }
    // Get the zip from the address

    public String getZip() {
        return zip;
    }
    // replace the street in the address

    public void setStreet(String street) {
        this.street = street;
        if (street == null || street.length() == 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
    }
// replace the city in the address

    public void setCity(String city) {
        this.city = city;
        if (city == null || city.length() == 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
    }
//replace the state in the address

    public void setState(String state) {
        this.state = state;
        if (state == null || state.length() == 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
    }
//replace the zip in the address

    public void setZip(String zip) {
        this.zip = zip;
        if (zip == null || zip.length() == 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
    }

    @Override
    //Format and return object's data in json format.
    public String toString() {
        return "<Address>"
                + "<street:>" + street + "</street>" + "\n"
                + "<city:>" + city + "</city>" + "\n"
                + "<State:>" + state + "</state>" + "\n"
                + "<Zipcode:>" + zip + "</zipcode>" + "\n"
                + "</address>";

    }

    @Override
    //Create and return a copy of the object.
    public Address clone() {
        Address add = new Address(street, city, state, zip);
        //This will return the new address(this.street, this.city, this.state, this.zip)
        return add;
    }

    // overload method has the same name as other methods but different parameters
    public Address clone(String line) {
        return new Address(line);
    }

}
