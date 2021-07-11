/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useraccounts;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import useraccounts.Exceptions.DuplicateObjectException;
import useraccounts.Exceptions.InvalidLoadException;

/**
 *
 * @author Salim
 */
public class main {

    static String path = "C://tmp-umuc/"; // all files will be saved here/ also, here where each account will be saved separately.
    static String Account_fileName = path + "account_id_number.json";

    /**
     * @param args the command line arguments
     */
    /**
     *
     * @return @throws java.io.IOException
     * @throws org.json.simple.parser.ParseException
     */
    public static AccountManager setup() throws IOException, ParseException {
        //Create a manager.
        AccountManager manager = new AccountManager(path);

        
        Address A = new Address("555 Belmawre Ct", "millersville", "MD", "21108");
        Owner Sami = new Owner("Sami", "salimsami01@outlook.com", "443-942-3649", "Salim", A);
        Account account = new Account(112233, Sami);
        manager.addAccount(account);

        return manager;

    }

    public static void main(String args[]) throws IOException, ParseException {

        addEquipmentToAccount();
        inactivateAnAccount();
        removingEquipmentFromAccount();
        loadAccountsOnStartup();
        rentingEquipment();
        removeAnAccount();
        purchasingEquipment();
//// Note---------Please try the exceptions below individually--------Otherwise it will show an error on the first one and not run the next. 
//        TestDuplicateObjectException();
//            TestInvalidCompletionException();
//        TestInvalidEquipmentException();
//        TestInvalidLoadException();
//        TestInvalidOperationException();
    }
    // load accounts on startup.
    static public void loadAccountsOnStartup() {
        try {
            System.out.println("-------load accounts on startup");
            AccountManager manager = new AccountManager(path);
            manager = setup(); // I added this to ensure it saves and load accounts
            Address E = new Address("7871 Ameriacana Cir", "Glenburnie", "MD", "21060");
            Owner Mirealla = new Owner("Mirealla ", "Mireallasalim@gmail.com", "443-278-4097", "Salim", E);
            StationaryBike equ3 = new StationaryBike("ST123456", "Golden55445", 599F, 12, 25);
            Account account3 = new Account(334455, Mirealla);
            account3.addEquipment(equ3);
            manager.addAccount(account3);
            JSONParser jsonParser = new JSONParser();
            manager.saveToFile(Account_fileName);
            FileReader reader = new FileReader(Account_fileName);
            Object obj = jsonParser.parse(reader);
            JSONArray accountList = (JSONArray) obj;
            System.out.println(accountList);

        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
    }

    // This will test the remove an account method. 
    static public void removeAnAccount() {
        try {
            System.out.println("-------Remove an account");
            AccountManager manager = new AccountManager(path);
            manager = setup();
            Address E = new Address("7871 Ameriacana Cir", "Glenburnie", "MD", "21060");
            Owner Mirealla = new Owner("Mirealla ", "Mireallasalim@gmail.com", "443-278-4097", "Salim", E);
            StationaryBike equ3 = new StationaryBike("ST123456", "Golden55445", 599F, 12, 25);
            Account account3 = new Account(334455, Mirealla);
            account3.addEquipment(equ3);
            manager.addAccount(account3); // add account with id number 334455
            manager.removeAccount(112233);// this will remove account 112233 and the file of the account. 
            manager.saveToFile(Account_fileName); // save the changes.
        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
    }

    // This will test the addEquipmentToAccount method in the system.
    static public void addEquipmentToAccount() {
        try {
            System.out.println("------adding equipment to the account-------");
            AccountManager manager = setup();
            Treadmill blue = new Treadmill("TH112233", "Golden55445", 599F, 16);
            manager.addEquipmentToAccount(blue, 112233);
            System.out.println("Treadmill blue added--------" + "serial number \"TH112233\"");
            ArrayList<Account> list = manager.getAccounts();
            for (int x = 0; x < list.size(); x++) {
                System.out.println(list.get(x)); //print list of equipment in the acount including the blue Treadmill.
            }
        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
    }
    // this will test the complete transaction method as renting.
    static public void rentingEquipment() {
        try {
            System.out.println("rentimg Equipment ----------");
            AccountManager manager = new AccountManager(path);
            manager = setup();
            Treadmill blue = new Treadmill("TH112233", "Golden55445", 599F, 16);
            manager.addEquipmentToAccount(blue, 112233);
            Stepper yellow = new Stepper("ST554455", "planet4565",299.99f,true,12);
            manager.addEquipmentToAccount(yellow, 112233);
            manager.completeEquipmentTransaction(112233, "TH112233", 2);
            manager.completeEquipmentTransaction(112233, "ST554455", 2);
            ArrayList<Account> list = manager.getAccounts();
            for (int x = 0; x < list.size(); x++) {
                System.out.println(list.get(x));
                
            }
           
        } catch (ParseException | IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
    }
// this will test the complete transaction method as purchasing.
    static public void purchasingEquipment() {
        try {
            System.out.println("purchasing Equipment ----------");
            AccountManager manager = null;

            manager = new AccountManager(path);

            manager = setup();

            Treadmill blue = new Treadmill("TH112233", "Golden55445", 599F, 16);
            manager.addEquipmentToAccount(blue, 112233);

            manager.completeEquipmentTransaction(112233, "TH112233", 1);
            manager.saveToFile(Account_fileName); // save the changes.
            System.out.println(blue);

        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
    }
    // this will test the removingEquipmentFromAccount method in the system. 
    static public void removingEquipmentFromAccount() {
        try {
            System.out.println("-------removing equipment from account-------");
            AccountManager manager = setup();
            Treadmill blue = new Treadmill("TH112233", "Golden55445", 599F, 16);
            manager.addEquipmentToAccount(blue, 112233); // add first equipment
            Treadmill equ1 = new Treadmill("TH123456", "Golden55445", 599F, 16);
            manager.addEquipmentToAccount(equ1, 112233); // add second equipment
             ArrayList<Account> list = manager.getAccounts();
            for (int x = 0; x < list.size(); x++) {
                System.out.println(list.get(x)); // print list of equipment with blue equipment "TH123456" in the account;
            }
            manager.removeEquipmentFromAccount(112233, "TH112233"); // delete the first equipment "TH112233"
            System.out.println("Treadmill blue removed: " + blue.serialNumber);
            for (int x = 0; x < list.size(); x++) {
                System.out.println(list.get(x)); // print list of equipment that left in the account; it should be the second equipment.
            }
        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
    }

    // inactivate an account. This method will print the account first with status true (activate),
    // after changing it to inactive the same account will be printed with status changed to false (inactivate) 
    static public void inactivateAnAccount() {
        
        try {
            AccountManager manager = setup();
            System.out.println("-------inactivate an account--------");
            Address a = new Address("1103 Double chestnut ct","Baltimore","MD","21226");
            Owner b = new Owner("Sami","salimsami01@outlook.com","443-942-3649","Salim",a);
            Treadmill D = new Treadmill("TH123456","btsh45566",123f,12);
            Stepper E = new Stepper("TH123456","btsh45566",123f,true,12);
            Account account4 = new Account(665544,b);
            manager.addAccount(account4);
                System.out.println(account4); // print the account as status is true
                manager.inactivateAccount(665544);
                ArrayList<Account> account =  manager.getAccounts();
            int size = account.size();
        for (int i = 0; i < size; i++) {
            
                System.out.println(manager.getAccounts());break; // print the list of accounts with 665544 status false.
                
                
            }

        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);

        }
    }

    static public void updateFileAfterChange() {

    }

    // This method will try to add two equipment to the account with same serial number. 
    // the Exception should catch it and print a message with serial number. 
    static public void TestDuplicateObjectException() {
        System.out.println("-------------TestDuplicateObjectException--------------");
        try {
            AccountManager manager = new AccountManager(path);

            Address E = new Address("7871 Ameriacana Cir", "Glenburnie", "MD", "21060");
            Owner Mirealla = new Owner("Mirealla ", "Mireallasalim@gmail.com", "443-278-4097", "Salim", E);
            StationaryBike equ3 = new StationaryBike("ST123456", "Golden55445", 599F, 12, 25);
            StationaryBike equ4 = new StationaryBike("ST123456", "Golden55445", 599F, 12, 25);
            Account account3 = new Account(334455, Mirealla);
            manager.addAccount(account3);// add an account.

            manager.addEquipmentToAccount(equ4, 334455); // Add an equipment to the account
            manager.addEquipmentToAccount(equ3, 334455); // try to add an equipment with same serial number. 

        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);

        }

    }
    // trying to remove equipment that has complete transaction.
    static public void TestInvalidCompletionException(){
     try {
            System.out.println();
            AccountManager manager = new AccountManager(path);
            Address E = new Address("7871 Ameriacana Cir", "Glenburnie", "MD", "21060");
            Owner Mirealla = new Owner("Mirealla ", "Mireallasalim@gmail.com", "443-278-4097", "Salim", E);
            StationaryBike equ3 = new StationaryBike("ST123456", "Golden55445", 599F, 12, 25);
            Account account3 = new Account(334455, Mirealla);
            account3.addEquipment(equ3);
            manager.addAccount(account3); // add account with id number 334455
            equ3.setStatus(1);
            manager.completeEquipmentTransaction(334455, "ST123456", 1); // complete transaction as purchase.
            
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
    
    }
    // this will test the InvalidEquipmentException when trying to add an equipment will null serial number.
    static public void TestInvalidEquipmentException() {
        System.out.println("--------InvalidEquipmentException------");
        try {
            AccountManager manager = new AccountManager(path);
            Address E = new Address("7871 Ameriacana Cir", "Glenburnie", "MD", "21060");
            Owner Mirealla = new Owner("Mirealla ", "Mireallasalim@gmail.com", "443-278-4097", "Salim", E);
            StationaryBike equ4 = new StationaryBike(" ", "Golden55445", 599F, 12, 25);
            Account account3 = new Account(334455, Mirealla);
            manager.addAccount(account3);// add an account.
            manager.addEquipmentToAccount(equ4, 334455);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);

        }
    }
// this will test the InvalidLoadException when tring to read from unknown file.
    static public void TestInvalidLoadException() {
        System.out.println("--------InvalidLoadException-------");
        try {
            AccountManager manager = setup();
            JSONParser jsonParser = new JSONParser();
            manager.saveToFile(main.path + Account_fileName);
            FileReader reader = new FileReader("account_id_number");
            Object obj = jsonParser.parse(reader);
            JSONArray accountList = (JSONArray) obj;
            System.out.println(accountList);

        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }

    }
    //this will test the InvalidOperationException.
    // after purchasing trying to remove the equipment the Exception will stop it and give a message.
    static public void TestInvalidOperationException() {

        try {
            System.out.println("purchasing Equipment ----then try to remove it.------");
            AccountManager manager = null;

            manager = new AccountManager(path);

            manager = setup();

            Treadmill blue = new Treadmill("TH112233", "Golden55445", 599F, 16);
            manager.addEquipmentToAccount(blue, 112233);

            manager.completeEquipmentTransaction(112233, "TH112233", 1);
            manager.saveToFile(Account_fileName); // save the changes.
            System.out.println(blue);

            manager.removeEquipmentFromAccount(112233, "TH112233"); // when I try to remove this equipment it will fail because already completed.

        } catch (ParseException e) {
            throw new InvalidLoadException(Account_fileName);
        } catch (IOException e) {
            throw new InvalidLoadException(Account_fileName);
        }
//
    }
}
