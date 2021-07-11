/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useraccounts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import useraccounts.Exceptions.DuplicateObjectException;
import useraccounts.Exceptions.InvalidOperationException;
import useraccounts.Google.RuntimeTypeAdapterFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import useraccounts.Exceptions.InvalidLoadException;

/**
 *
 * @author Salim
 */
public class AccountManager {

    private ArrayList<Account> accounts = new ArrayList<Account>();
    private String accountPath = "C://tmp-umuc/";
    private String Account_fileName = accountPath + "account_id_number.json";

    private Gson getEquipmentSerializer() {

        RuntimeTypeAdapterFactory<Equipment> resAdapter = RuntimeTypeAdapterFactory.of(Equipment.class, Equipment.class.getName())
                .registerSubtype(Treadmill.class, Treadmill.class.getName())
                .registerSubtype(Stepper.class, Stepper.class.getName())
                .registerSubtype(StationaryBike.class, StationaryBike.class.getName());

        return new GsonBuilder().registerTypeAdapterFactory(resAdapter).create();
    }

    public void saveToFile(Account account) throws IOException {
        File file = new File(accountPath + "account-" + account.getId_number() + ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, account.clone());
    }

    //run on startup
    public AccountManager(String accountPath) {
        try {
            JSONParser jsonParser = new JSONParser();
            saveToFile(Account_fileName);
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

    // return clone of list and object so cannot be changed directly
    public ArrayList<Account> getAccounts() {
        ArrayList<Account> ac = new ArrayList<Account>();
        for (Account acc : accounts) {

            ac.add(acc.clone());// add clone objects
        }
        return ac;

    }

    public String getAccoutPath() {
        return accountPath;
    }

    // Add an account to the list.
    public void addAccount(Account a) throws FileNotFoundException, IOException {
        if (a == null) {
            throw new IllegalArgumentException("Account cant be null");
        }

        //check if the account already exists 
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getId_number().equals(a.getId_number())) {
                throw new DuplicateObjectException("Account already exists" + a.getId_number());
            }
            break;
        }
        accounts.add(a.clone());
        saveToFile(a);
    }

    // Save the new account into a file based on the account Id number.
    // Open list of accounts from the file.
    private void openFromFile(JSONObject account) {
        JSONObject accountD = (JSONObject) account.get("account");
        String id_number = (String) accountD.get("id_number");
        String owner = (String) accountD.get("Owner");
        String isActivateAccount = (String) accountD.get("isActivateAccount");
        JSONArray equipmentArray = (JSONArray) accountD.get("equipment");
        Iterator<JSONObject> iterator = equipmentArray.iterator();
        while (iterator.hasNext()) {
        }
    }

    // Remove an account from the list by ID number.
    public void removeAccount(Integer id_number) throws IOException {

        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            Vector<Equipment> equipment;
            if (!accounts.get(i).getId_number().equals(id_number)) {
                throw new InvalidOperationException("Can't find the id number" + id_number);
            }

            accounts.remove(i);
            saveToFile(account);

            File myobj = new File(accountPath + "account-" + account.getId_number() + ".json");
            myobj.delete();

        }

    }

    //Change an existing account to inactive.
    public void inactivateAccount(Integer id_number) throws IOException {
        int size = accounts.size();
        for (int i = 0; i < size; i++) {
            Account account = accounts.get(i);
            if (accounts.get(i).isActivateAccount() == false) {
                throw new IllegalStateException("account already inactive");
            }

            if (accounts.get(i).getId_number().equals(id_number)) {
                account.inActivateAccount(id_number);
            }

            saveToFile(account);
        }

    }

    // Remove equipment object from an account by the serial number of the equipment.
    public void removeEquipmentFromAccount(Integer id_number, String serialNumber) throws FileNotFoundException, IOException {

        int size = accounts.size();
        for (int i = 0; i < size; i++) { // loop to find the id number
            Account account = accounts.get(i);
            if (accounts.get(i).getEquipments().equals(serialNumber)) {
                throw new DuplicateObjectException(serialNumber);//If the equipemnt not found throw InvalidEquipmentException exception (is in the Account Class)

            }
            if (!accounts.get(i).getId_number().equals(id_number)) {
                throw new InvalidOperationException("Can't find the id number" + id_number);
            }
            account.removeEquipment(serialNumber); // the manager gets the method from Account class
            saveToFile(account);
        }

    }

    // Add equipment object to the account to be rented or purchased.
    public void addEquipmentToAccount(Equipment equipment, Integer id_number) throws FileNotFoundException, IOException {

        int size = accounts.size();

        for (int i = 0; i < size; i++) { // loop to find the serialnumber

            Account account = accounts.get(i);
            if (!accounts.get(i).getId_number().equals(id_number)) {
                throw new InvalidOperationException("Can't find the id number" + id_number);
            }

            if (accounts.get(i).getId_number().equals(id_number)) {
                account.addEquipment(equipment);
//                
            }

//           
            saveToFile(account);

        }

    }
    // Update the equipment status to either bought or rented.

    public void completeEquipmentTransaction(Integer id_number, String serialNumber, int status) throws IOException {
        if (status == 1) {
            int size = accounts.size();
            for (int i = 0; i < size; i++) {
                if (!accounts.get(i).getId_number().equals(id_number)) {
                    throw new InvalidOperationException("Can't find the id number" + id_number);
                }
                if (accounts.get(i).isActivateAccount() == false) {
                    throw new IllegalArgumentException("account is not active ");
                }
                Account account = accounts.get(i);
                accounts.get(i).completeTransaction(serialNumber, 1);
                saveToFile(account);

            }
        }

        if (status == 2) {
            int size = accounts.size();
            for (int i = 0; i < size; i++) {

                Account account = accounts.get(i);
                if (!accounts.get(i).getId_number().equals(id_number)) {
                    throw new InvalidOperationException("Can't find the id number" + id_number);
                }
                if (accounts.get(i).isActivateAccount() == false) {
                    throw new IllegalArgumentException("account is not active ");
                }
                accounts.get(i).completeTransaction(serialNumber, 2);
                saveToFile(account);
                saveToFile(Account_fileName);

            }

        }
        saveToFile(Account_fileName);
    }

    // this method to save accounts in the "account_id_number.json" for the constructor to load on start up.
    public void saveToFile(String path) throws IOException {
        File file = new File(Account_fileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, accounts);
    }

}
