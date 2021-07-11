/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useraccounts;

import useraccounts.Exceptions.InvalidCompletionException;
import useraccounts.Exceptions.InvalidEquipmentException;

/**
 *
 * @author Salim
 */
public abstract class Equipment {

    protected String serialNumber; // Unique serial number for equipment using UI.

    protected String brandModel; //Brand and model (e.g. “Horizon Fitness X685”).

    protected float equipmentPrice; // The price of the equipment.

    protected int status;// The number of equipment that was purchased or rented (for example 1 bought or 1 rented).

    protected float shippingPrice;

    protected float transactionPrice; // This is the transaction price that is stored and managed after the transaction is completed.

    /*
  * Any invalid or missing values throw IllegalArgumentException
     */
    public Equipment(String serialNumber, String brandModel, float equipmentPrice) throws IllegalArgumentException {
        this.serialNumber = serialNumber;
        this.brandModel = brandModel;
        this.equipmentPrice = equipmentPrice;
        if (serialNumber == null || serialNumber.length() == 0 || brandModel == null || brandModel.length() == 0 || equipmentPrice <= 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
        if (!serialNumber.matches("[T-zH-Z]{2}\\d{3,6}") || !serialNumber.matches("[T-zH-Z]{2}\\d{3,6}") || !serialNumber.matches("[S-zB-Z]{2}\\d{3,6}")) {
            throw new InvalidEquipmentException(serialNumber + "Must be in the formate (2 letters plus 6 digits where letters are TH, ST, or SB)" + "SerialNumber:");
        }

    }

    public void setStatus(int status) {
        this.status = status;

    }

    // it returns the value of the serial number
    public String getSerialNumber() {
        return serialNumber;
    }

    // it returns the brand & model
    public String getBrandModel() {
        return brandModel;
    }

    // it returns the price of the equipment
    public float getEquipmentPrice() {
        return equipmentPrice;
    }
    // it returns the price of the status

    public int getStatus() {
        return status;
    }

    public float getShippingPrice() {

        return shippingPrice;
    }

    public float calcTransactionPrice(int status) {
        if (status == 1) {
            transactionPrice = (float) (equipmentPrice + calcShipping());
        } else if (status == 2) {
            transactionPrice = (float) (equipmentPrice * 0.15 + calcShipping());
        }

        return transactionPrice;

    }

    // it returns the price of the transaction Price
    public float getTransactionPrice() {
        return transactionPrice;
    }

    public String completeTransaction(String serialNumber) {
        String equ = this.serialNumber;
        if (equ.equals(serialNumber)) {
            if (status == 1 || status == 2) {
                throw new InvalidCompletionException("Equipment not avalible ");

            }
        }
        return equ;

    }

    protected float calcShipping() {

        return shippingPrice;

    }

    @Override
    //Format and return the object's data in json format.
    public String toString() {

        return "<serialNumber>" + serialNumber + "</serialNumber>" + "\n"
                + "<brandModel>" + brandModel + "</brandModel>" + "\n"
                + "<equipmentPrice>" + equipmentPrice + "</equipmentPrice>" + "\n"
                + "<shippingPrice>" + shippingPrice + "</shippingPrice>" + "\n";

    }

    @Override

    public abstract Equipment clone(); // abstract method so that children must implement clone

    public abstract Equipment clone(String line);  // abstract method so that children must implement clone

    protected Equipment(String line) {

        serialNumber = line.substring(line.indexOf("<serialNumber>") + 12, line.indexOf("</serialNumber>"));
        brandModel = line.substring(line.indexOf("<brandModel>") + 12, line.indexOf("</brandModel>"));
        equipmentPrice = Float.parseFloat(line.substring(line.indexOf("<equipmentPrice>") + 17, line.indexOf("</equipmentPrice>")));
        shippingPrice = Float.parseFloat(line.substring(line.indexOf("<shippingPrice>") + 15, line.indexOf("</shippingPrice>")));
        if (serialNumber == null || serialNumber.length() == 0 || brandModel == null || brandModel.length() == 0 || equipmentPrice <= 0) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
        if (!serialNumber.matches("[T-zH-Z]{2}\\d{3,6}") || !serialNumber.matches("[T-zH-Z]{2}\\d{3,6}") || !serialNumber.matches("[S-zB-Z]{2}\\d{3,6}")) {
            throw new InvalidEquipmentException(serialNumber + "Must be in the formate (2 letters plus 6 digits where letters are TH, ST, or SB)" + "SerialNumber:");
        }
    }

}
