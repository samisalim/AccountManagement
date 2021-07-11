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
public class InvalidCompletionException extends RuntimeException {

    private String equipmentS;

    public InvalidCompletionException(String equipmentS) {
        super("equipment transaction couldn't be completed for: " + equipmentS);
        this.equipmentS = equipmentS;
    }

    /*
* call super message: "equipment transaction couldn't be completed for" + equipmentID + cause
* assign parameter equipmentS to attribute equipmentS
     */

    public String toString() {

        return this.getClass().getSimpleName() + " equipment: " + equipmentS+ "transaction can't be complete" ;
        /*
        * after generate the system will return a message: "  the equipment id and details why exactly it failed.
         */
    }
}
