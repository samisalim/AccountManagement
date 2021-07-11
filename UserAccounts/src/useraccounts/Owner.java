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
// This is the owner's information. 
public class Owner {

    private String firstName;

    private String email;

    private String phoneNumber;

    private String lastName;

    private Address mailingAdress;

    public Owner(String firstName, String email, String phoneNumber, String lastName, Address mailingAdress) {
        if (firstName == null || firstName.length() == 0 || email == null || email.length() == 0 || phoneNumber == null || lastName == null || lastName.length() == 0 || mailingAdress == null) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.mailingAdress = mailingAdress;
        /*
      * Validate parameters
      * Assign parameters's values to attributes 
         */
    }

    private Owner(String line) {

        firstName = line.substring(line.indexOf("<Firstname>") + 11, line.indexOf("</Firstname>"));
        lastName = line.substring(line.indexOf("<Lastname>") + 10, line.indexOf("</Lastname>"));
        email = line.substring(line.indexOf("<email>") + 7, line.indexOf("</email>"));
        phoneNumber = line.substring(line.indexOf("<phonenumber>") + 13, line.indexOf("</phonenumber>"));
        if (firstName == null || firstName.length() == 0 || email == null || email.length() == 0 || phoneNumber == null || lastName == null || lastName.length() == 0 || mailingAdress == null) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }

    }

    // return the value of the first name.
    public String getFirstName() {
        return firstName;
    }

    //// return the value of the email.
    public String getEmail() {
        return email;
    }

    // return the value of the phone number.
    public String getPhoneNumber() {
        return phoneNumber;
    }

// return the value of the last name.
    public String getLastName() {
        return lastName;
    }

    // return the value of the address.
    public Address getMailingAdress() {
        return mailingAdress;
    }

    // it takes a parameter and assigns it to first name. 
    public void setFirstName(String firstName) {

        this.firstName = firstName;
        if (firstName == null || firstName.length() == 0) {
            throw new IllegalArgumentException("first name can't be null");
        }
    }

    // it takes a parameter and assigns it to email.
    public void setEmail(String email) {
        this.email = email;
        if (email == null || email.length() == 0) {
            throw new IllegalArgumentException("email can't be null");
        }
    }
    // it takes a parameter and assigns it to a phone number.

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        if (phoneNumber == null || phoneNumber.length() == 0) {
            throw new IllegalArgumentException("phoneNumber can't be null");
        }
    }
    // it takes a parameter and assigns it to a last name.

    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (lastName == null || lastName.length() == 0) {
            throw new IllegalArgumentException("lastName can't be null");
        }
    }

    // it takes a parameter and assigns it to an address.
    public void setMailingAdress(Address mailingAdress) {
        this.mailingAdress = mailingAdress;
        if (mailingAdress == null) {
            throw new IllegalArgumentException("mailingAdress can't be null");
        }
    }

    @Override
    //Format and return the object's data in json format.
    public String toString() {
        return "<Owner>"
                + "<Firstname>" + firstName + "</First Name>" + "\n"
                + "<Lastname>" + lastName + "</Last name>" + "\n"
                + "<email>" + email + "</email>" + "\n"
                + "<phonenumber>" + phoneNumber + "</phone number>" + "\n"
                + "<Address>" + mailingAdress.getStreet() + " " + mailingAdress.getCity() + "\n "
                + mailingAdress.getState() + " " + mailingAdress.getZip() + "</Address>" + "\n"
                + "</Owner>";

    }

    @Override
    //Create and return a copy of the object.
    public Owner clone() {
        Owner ow = new Owner(firstName, lastName, email, phoneNumber, mailingAdress);

        return ow;
       
    }
    // overload method same name different parameters

    public Owner clone(String line) {

        return new Owner(line);
    }
}
