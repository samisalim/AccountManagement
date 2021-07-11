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
import useraccounts.Equipment;

/**
 *
 * @author Salim
 */
public class InvalidEquipmentException extends RuntimeException {

    private String equipmentS;
    private String equipmentBM;
    private float equipmentP;

    public InvalidEquipmentException(String equipmentS) {
        super("the serial number is invalid" + equipmentS);
        this.equipmentS = equipmentS;
    }

    /*
	* Call a super message with a message "the serial number is invalid" + equipmentS
	* assign the equipmentS to the equipment attribute
     */

    public InvalidEquipmentException(String equipmentBM, String equipmentS) {
        super("the equipment brand and model is invalid" + equipmentBM);
        this.equipmentBM = equipmentBM;
        this.equipmentS = equipmentS;
    }

    /*
	* call the super message "the equipment brand and model is invalid" +equipmentBM
	* assign the equipmentS to the equipment attribute
     */

    public InvalidEquipmentException(float equipmentP) {
        super("the equipment price is invalid" + equipmentP);
        this.equipmentP = equipmentP;
    }

    /*
	* call the super message "the equipment price is invalid" +equipmentP
	* assign the equipmentP to the equipment attribute
     */

    public String toString() {

        /*
        * after generate the system will return a message: " the name of the exeption + the message.
        * Foe example;
        * throw new IInvalidEquipmentException("equipment creation error" + e.getMessage());
         */
        return this.getClass().getSimpleName() + ":Some of the attributes are invalid or null " ;

    }
}
