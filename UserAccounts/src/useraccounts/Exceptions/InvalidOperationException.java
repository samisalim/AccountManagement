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
public class InvalidOperationException extends RuntimeException {

    private int accountID;
    private String equipmentS;

    public InvalidOperationException(int accountID) {
        super("the account can't be removed" + accountID);
        this.accountID = accountID;
    }

    /*
	* call a super message "can’t be removed" + accoutID + cause
	* assign accountID to accountID attribute.
     */

    public InvalidOperationException(String equipmentS) {
        super("the serial number can't be found"+equipmentS);
        this.equipmentS = equipmentS;
    }

    /*
* * call a super message "can’t be removed" + accoutID + cause
     */

    
    public String toString() {

        return this.getClass().getTypeName()+ " "+": this account: "+ "/" + accountID+"/" + "  or this serial number: "+ "/"+ equipmentS +"/" + " is already completed or not found" + "/" ;
    }
    
}
