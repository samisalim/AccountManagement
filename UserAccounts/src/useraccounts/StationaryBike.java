/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useraccounts;

import useraccounts.Exceptions.InvalidEquipmentException;

/**
 *
 * @author Salim
 */
public class StationaryBike extends Equipment {

    private int resistanceLevel;

    private int hight;

    public StationaryBike(String serialNumber, String brandModel, float equipmentPrice, int resistanceLevel, int hight) throws IllegalArgumentException {
        super(serialNumber, brandModel, equipmentPrice);
        this.hight = hight;
        this.resistanceLevel = resistanceLevel;
        if (hight <= 0) {
            throw new InvalidEquipmentException(serialNumber + "hieght can be 0 or lower");
        }
        if (resistanceLevel < 0) {
            throw new InvalidEquipmentException(serialNumber + "resistance Level can be lower than 0");
        }

        calcShipping();

    }

    private StationaryBike(String line) {
        super(line);
        resistanceLevel = Integer.parseInt(line.substring(line.indexOf("<resistanceLevel>")) + 17, line.indexOf("</resistanceLevel>"));
        hight = Integer.parseInt(line.substring(line.indexOf("<hight>")) + 7, line.indexOf("</hight>"));
        if (!serialNumber.matches("[S-zB-Z]{2}\\d{6}")) {
            throw new InvalidEquipmentException(serialNumber + "Must be in the formate (2 letters plus 6 digits where letters are TH, ST, or SB)" + "SerialNumber:");
        }
        if (equipmentPrice <= 0) {
            throw new InvalidEquipmentException(serialNumber + "price cant be 0 or less" + "SerialNumber:");
        }
    }

    // When the user changes the transaction to a rental, this will calculate the rental price and override the calcTransactionPrice()
    // in the equipment class.
    @Override
    protected float calcShipping() {
        if (hight >= 5) {
            this.shippingPrice = 39.98f;
        } else {
            this.shippingPrice = 29.99f;
        }
        /*
    * If hight > 5 then 39.98 
     * Else shipping price = 29.99
     * return shippingPrice
         */
        return this.shippingPrice;
    }

    // it returns the value of the resistance level
    public int getResistanceLevel() {
        return resistanceLevel;
    }

    // it returns the value of the height
    public int getHight() {
        return hight;
    }
    // it returns the value of the equipment Price

    @Override
    //Format and return the object's data in json format.
    public String toString() {
        return "<StationaryBike>"
               
                + "<serialNumber>" + serialNumber + "</serialNumber>" + "\n"
                + "<brandModel>" + brandModel + "</brandModel:>" + "\n"
                + "<equipmentPrice>" + equipmentPrice + "</equipmentPrice>" + "\n"
                + "<resistanceLevel>" + resistanceLevel + "</resistanceLevel>" + "\n"
                + "<hight>" + hight + "</hight>" + "/n"
                + "<TransactionPrice>" + transactionPrice + "</TransactionPrice>" + "/n"
                +"<ShippingPrice>" + shippingPrice + "</ShippingPrice>" + "\n"
                + "<StationaryBike>";
    }

    @Override
    public StationaryBike clone() {
        StationaryBike s = new StationaryBike(serialNumber, brandModel, equipmentPrice, hight, resistanceLevel);
        s.status= this.status;
        s.shippingPrice = this.shippingPrice;
        s.transactionPrice = this.transactionPrice;
        return s;
    }

    @Override
    // overload method with the same name but different parameters
    public StationaryBike clone(String line) {

        return new StationaryBike(line);
    }

}
