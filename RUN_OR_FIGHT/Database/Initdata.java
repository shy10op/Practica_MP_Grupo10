package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import User.User;
import User.Player;
import Character.Equipment.*;
import User.RecordPlayer;

public class Initdata implements Serializable {
    private static final String FILENAME = "users.dat";
    private static final String INVENTORY = "inventory.dat";
    private static ArrayList<Inventory> inventories = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();

    private static void checkUsersFile() {
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("User file not found. Creating new user database.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkInventoriesFile() {
        File file = new File(INVENTORY);
        if (!file.exists()) {
            System.out.println("Inventory file not found. Creating new inventory database.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadUsersFromFile() {
        checkUsersFile(); 
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadInventoriesFromFile() {
        checkInventoriesFile();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(INVENTORY))) {
            inventories = (ArrayList<Inventory>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, false))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveInventoriesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INVENTORY, false))) {
            oos.writeObject(inventories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateBots() {
        if (!users.isEmpty()) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNick("Bot" + i);
            user.setPassword("12345");
            user.setName("BotName" + i);
            user.setRole("player");
            String record = RecordPlayer.generateRecord();
            Player newPlayer = new Player(user.getNick(),user.getName(),record);
            user.setPlayer(newPlayer);
            users.add(user);
        }
        saveUsersToFile();
    }

    public static void generateInitialInventories() {
        if (!inventories.isEmpty()) {
            return;
        }
        Inventory inventory = new Inventory();
        Weapon weapon1 = EquipmentFactory.createWeapon("Longsword", 3, 1, 2);
        Weapon weapon2 = EquipmentFactory.createWeapon("Short Sword", 2, 0, 1);
        Weapon weapon3 = EquipmentFactory.createWeapon("One-handed axe", 2, 1, 1);
        Weapon weapon4 = EquipmentFactory.createWeapon("Axe", 3, 2, 2);
        Weapon weapon5 = EquipmentFactory.createWeapon("Spear", 2, 1, 2);
        inventory.addWeapon(weapon1);
        inventory.addWeapon(weapon2);
        inventory.addWeapon(weapon3);
        inventory.addWeapon(weapon4);
        inventory.addWeapon(weapon5);
        Armor armor1 = EquipmentFactory.createArmor("Bronze Armor", 0, 2);
        Armor armor2 = EquipmentFactory.createArmor("Aluminum Armor", 2, 1);
        Armor armor3 = EquipmentFactory.createArmor("Silver Armor", 1, 2);
        Armor armor4 = EquipmentFactory.createArmor("Gold Armor", 0, 2);
        Armor armor5 = EquipmentFactory.createArmor("Titanium Armor", 2, 3);
        inventory.addArmor(armor1);
        inventory.addArmor(armor2);
        inventory.addArmor(armor3);
        inventory.addArmor(armor4);
        inventory.addArmor(armor5);

        inventories.add(inventory);
        saveInventoriesToFile();
    }

    public static void startInitData() {
        loadUsersFromFile();
        generateBots();
        loadInventoriesFromFile();
        generateInitialInventories();
    }

    public static String getFILENAME() {
        return FILENAME;
    }

    public static String getINVENTORY() {
        return INVENTORY;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Inventory> getInventories() {
        return inventories;
    }

}