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
public class Stepper extends Equipment {

    private boolean heartMonitor;

    private int hight;

    public Stepper(String serialNumber, String brandModel, float equipmentPrice, boolean heartMonitor, int hight) {
        super(serialNumber, brandModel, equipmentPrice);
        this.heartMonitor = heartMonitor;
        this.hight = hight;

        if (hight <= 0) {
            throw new InvalidEquipmentException(serialNumber + "hight cant be 0 or lower");
        }

        calcShipping();

    }

    private Stepper(String line) {
        super(line);
        hight = Integer.parseInt(line.substring(line.indexOf("<hight>")) + 7, line.indexOf("</hight>"));
        heartMonitor = Boolean.parseBoolean(line.substring(line.indexOf("<heartMonitor>") + 14, line.indexOf("</heartMonitor>")));
        if (hight <= 0) {
            throw new InvalidEquipmentException(serialNumber + "hight cant be 0 or lower");
        }

    }
// it returns if the Stepper has a heart monitor or not

    public boolean getHeartMonitor() {
        return heartMonitor;
    }

// it returns the height of the stepper
    public int getHight() {
        return hight;
    }

    @Override
    protected float calcShipping() {
        if (this.getHight() > 5) {
            this.shippingPrice = 39.98f;
        } else {
            this.shippingPrice = 29.99f;
        }
        /*
    * If hight > 5 then 39.98 
     * Else shipping price = 29.99
     * return shippingPrice
         */
        return shippingPrice;
    }

    @Override
    //Format and return the object's data in json format.
    public String toString() {
        return "<Stepper>"
//                
                + "<serialNumber>" + serialNumber + "</serialNumber>" + "\n"
                + "<brandModel>" + brandModel + "</brandModel>" + "\n"
                + "<equipmentPrice>" + equipmentPrice + "</equipmentPrice>" + "\n"
                + "<heartMonitor>" + heartMonitor + "</heartMonitor>" + "\n"
                + "<hight>" + hight + "</hight>" + "\n"
                + "<Status>" + status + "</Status>" + "\n"
                + "<TransactionPrice>" + transactionPrice + "</TransactionPrice>" + "\n"
                +"<ShippingPrice>" + shippingPrice + "</ShippingPrice>" + "\n"
                + "</Stepper>";
    }

    @Override
    //Create and return a copy of the object
    public Stepper clone() {
        Stepper s = new Stepper(serialNumber, brandModel, equipmentPrice, heartMonitor, hight);
        s.shippingPrice = this.shippingPrice;
        s.status = this.status;
        s.transactionPrice = this.transactionPrice;
        return s;
    }

    @Override
    // overload method same name different parameters
    public Stepper clone(String line) {

        return new Stepper(line);
    }
}
