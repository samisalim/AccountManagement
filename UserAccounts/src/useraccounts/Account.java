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
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;
import useraccounts.Exceptions.DuplicateObjectException;
import useraccounts.Exceptions.InvalidCompletionException;
import useraccounts.Exceptions.InvalidLoadException;
import useraccounts.Exceptions.InvalidOperationException;

public class Account {

    // Unique ID number for each account.
    private Integer id_number;

    // Owner information from the owner class.
    private Owner owner;

    // Manage and store if the account is active or not. If the account is active then it's (True)
    //otherwise it's (False) for inactive. 
    private boolean isActivateAccount;

    private Vector<Equipment> equipments;

    /**
     *
     * @element-type Equipment
     */
    // Constructor to call and to to create an instance of that class.
    public Account(Integer id_number, Owner owner) {

        this.id_number = id_number;
        this.owner = owner;
        this.equipments = new Vector<Equipment>(); // create an empty Vector object for equipment
        this.isActivateAccount = true; // set the status as active when creating an account.
        if (id_number == null || owner == null) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }
    }

    public boolean isActivateAccount() {
        return isActivateAccount;
    }

    public Account(String filename) {
        Gson json = new Gson();
        try {

            Account v = json.fromJson(new FileReader(filename), Account.class);
            this.id_number = v.getId_number();
            this.isActivateAccount = v.inActivateAccount(id_number);
            this.owner = v.getOwner();
            this.equipments = v.getEquipments();
            if (id_number == null || owner == null) {
                throw new IllegalArgumentException("no Attributes  should be null");
            }
        } catch (FileNotFoundException e) {
            throw new InvalidLoadException(filename);
        }
    }

    // returns the value of the id_number
    public Integer getId_number() {
        return id_number;
    }

    //replaces the owner of the account
    public Owner getOwner() {

        return owner.clone();

    }

    public void setOwner(Owner owner) {
        this.owner = owner.clone();

        if (this.isActivateAccount = false) {
            throw new IllegalArgumentException("Cannot update owner information for inactive account");
        }
        if (owner.getFirstName() == null || owner.getEmail() == null || owner.getPhoneNumber() == null || owner.getLastName() == null || owner.getMailingAdress() == null) {
            throw new IllegalArgumentException("no Attributes  should be null");
        }

    }

    //this is used to retrieve or get an element for the vector
    public Vector<Equipment> getEquipments() {
        Vector<Equipment> equ = new Vector<Equipment>();
        for (Equipment equi : equipments) {
            equ.add(equi.clone()); // add clone objects
        }
        return equ;
    }

    // Add equipment to the vector of equipment and return number of equipment. 
    public void addEquipment(Equipment equipment) {

        if (equipment == null) {
            throw new IllegalArgumentException("Passed value is null");
        }
        int size = equipments.size();
        for (int i = 0; i < size; i++) {
            if (equipments.get(i).getSerialNumber().equalsIgnoreCase(equipment.getSerialNumber())) {
                throw new DuplicateObjectException(equipment.serialNumber);
            }

        }
        if (isActivateAccount == false) {
            throw new InvalidOperationException(id_number);
        }

        equipments.add(equipment);

    }

    // Remove equipment from the vector based on the serial number.
    public void removeEquipment(String serialNumber) {

        int size = equipments.size();
        for (int i = 0; i < size; i++) {
            if (!equipments.get(i).getSerialNumber().equalsIgnoreCase(serialNumber)) {
                throw new IllegalArgumentException("equipment dont exist");
            }
            if (isActivateAccount == false) {
                throw new InvalidOperationException(id_number);
            }
            if (equipments.get(i).status == 1 || equipments.get(i).status == 1) {
                throw new InvalidOperationException(serialNumber);
            }
            equipments.remove(i);
            if (size == equipments.size()) {// if  nothing was not removed or size didn't change
                throw new InvalidOperationException(serialNumber);
            }
            break;

        }
    }

// Identify the Account to complete transaction using the id_number
    public void completeTransaction(String serialNumber, int status) {
        if (status == 1) {
            int size = equipments.size();
            for (int i = 0; i < size; i++) {
                if (!equipments.get(i).getSerialNumber().equalsIgnoreCase(serialNumber)) {
                    throw new IllegalArgumentException("equipment dont exist");
                }

                if (equipments.get(i).serialNumber.equalsIgnoreCase(serialNumber)) {
                    equipments.get(i).completeTransaction(serialNumber);
                    equipments.get(i).calcTransactionPrice(1);
                    String serialNumber1 = equipments.get(i).serialNumber;

                }
                if (equipments.get(i).status == 0) {
                    equipments.get(i).setStatus(1);
                } else {
                    throw new InvalidCompletionException(serialNumber);
                }

            }

        }
        if (status == 2) {
            int size = equipments.size();
            for (int i = 0; i < size; i++) {
                if (equipments.get(i).serialNumber.equalsIgnoreCase(serialNumber)) {
                    equipments.get(i).completeTransaction(serialNumber);
                    equipments.get(i).calcTransactionPrice(2);
                    String serialNumber1 = equipments.get(i).serialNumber;
                    equipments.get(i).setStatus(2);

                }
            }
        }
    }

    // method to can called on specific account and do that action 
    public boolean inActivateAccount(Integer id_number) {

        if (isActivateAccount == false) {
            throw new IllegalStateException("account already inactive");

        }
        this.isActivateAccount = false;
        return isActivateAccount;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID:").append(id_number).append("\n")
                .append("owner: ").append(owner.toString()).append("\n")
                .append("Account status:").append(isActivateAccount).append("\n")
                .append("Equipment:").append(equipments).append("\n");

        return builder.toString();
    }

    @Override
    public Account clone() {
        Account a = new Account(id_number, owner);
        a.isActivateAccount = this.isActivateAccount;
        a.equipments = this.equipments;
        return a;
    }

}
