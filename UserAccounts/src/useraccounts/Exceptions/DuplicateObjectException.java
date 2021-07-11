/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useraccounts.Exceptions;

/**
 *
 * @author Salim
 */
public class DuplicateObjectException extends RuntimeException {

    private String equipmentS;
    private int accountID;

    public DuplicateObjectException(String equipmentS) {
        super("the equipment serial number already exist" + equipmentS);
        this.equipmentS = equipmentS;
    }

    /*
* call super message: “the equipment serial number already exist” + equipmentS + cause
* assign parameter equipmentS to attribute equipmentS.
     */
    public DuplicateObjectException(int accountID) {
        super("the account id already exists" + accountID);
        this.accountID = accountID;

    }

    /*
* call super message: the account id already exists + accountID + cause
* assign parameter accountID to attribute accountID.
     */
    public String toString() {
         

        
        return this.getClass().getSimpleName() + " this serialNumber " + "/" + equipmentS + "/" +" or this account "+ "/" + accountID + "/" +" already exist so you can't add it twice. ";
        /*
        * after generate the system will return a message: "  id of the account or equipment's serial number is duplicate the message.
        * Foe example;
        * throw new DuplicateObjectException("error on adding account or adding equipment to an account" + e.getMessage());
         */
    }
}
