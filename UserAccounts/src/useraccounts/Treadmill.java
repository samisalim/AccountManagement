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
public class Treadmill extends Equipment {

    private double maxSpeed;

    public Treadmill(String serialNumber, String brandModel, float equipmentPrice, double maxSpeed) throws IllegalArgumentException {
        super(serialNumber, brandModel, equipmentPrice);
        this.maxSpeed = maxSpeed;

        if (maxSpeed <= 0) {
            throw new InvalidEquipmentException(serialNumber + "maxSpeed cant be 0 or lower");
        }

    }

    private Treadmill(String line) {
        super(line);
        maxSpeed = Double.parseDouble(line.substring(line.indexOf("<maxSpeed>") + 10, line.indexOf("</maxSpeed>")) );
        if (maxSpeed <= 0) {
            throw new InvalidEquipmentException(serialNumber + "maxSpeed cant be 0 or lower");
        }
    }
    // it returns the max speed of the treadmill

    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    protected float calcShipping() {

        this.shippingPrice = 29.99f;
        return shippingPrice;
    }

    @Override
    //Format and return the object's data in json format.
    public String toString() {
        return "<Treadmill>"
               
                + "<serialNumber>" + serialNumber + "</serialNumber>" + "\n"
                + "<brandModel>" + brandModel + "</brandModel>" + "\n"
                + "<equipmentPrice>" + equipmentPrice + "</equipmentPrice>" + "\n"
                + "<maxSpeed>" + maxSpeed + "</maxSpeed>" + "\n"
                + "<Status>" + status + "</Status>" + "\n"
                + "<TransactionPrice>" + transactionPrice + "</TransactionPrice>" + "\n"
                + "<ShippingPrice>" + shippingPrice + "</ShippingPrice>"+ "\n"
                + "</Treadmill>";

    }

    @Override
    //Create and return a copy of the object.
    public Treadmill clone() {
        Treadmill t = new Treadmill(serialNumber, brandModel, equipmentPrice, maxSpeed);
        t.status = this.status;
        t.transactionPrice = this.transactionPrice;
        t.shippingPrice = this.shippingPrice;
        return t;
    }

    // overload method has the same name with different parameters
    public Treadmill clone(String line) {

        return new Treadmill(line);
    }

}
