package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import User.User;
import User.Admin;
import User.Player;
import Character.CharacterFactory;
import Character.Character;
import Character.Equipment.*;
import SystemFunction.Combate;
import User.Auth;

public class Initdata implements Serializable {
    private static final String FILENAME = "users.dat";
    private static final String INVENTORY = "inventory.dat";
    private static final String COMBATES = "combates.dat";
    private static Inventory inventories = new Inventory();
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Combate> combates = new ArrayList<>();

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

    private static void checkCombatesFile() {
        File file = new File(COMBATES);
        if (!file.exists()) {
            System.out.println("Combates file not found. Creating new combates database.");
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

    public static void loadInventoriesFromFile() {
        checkInventoriesFile();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(INVENTORY))) {
            inventories = (Inventory) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadCombatesFromFile() {
        checkCombatesFile();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COMBATES))) {
            combates = (ArrayList<Combate>) ois.readObject();
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

    public static void saveCombatesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COMBATES, false))) {
            oos.writeObject(combates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateBots() {

        Random random = new Random();
        if (!users.isEmpty()) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNick("Bot" + i);
            user.setPassword("12345");
            user.setName("BotName" + i);
            user.setRole("player");
            String record = Auth.generateRecord();
            Player newPlayer = new Player(user.getNick(), user.getName(), record);
            user.setPlayer(newPlayer);

            Weapon botWeapon = EquipmentFactory.createWeapon("botweapon", 2, 2, 2);
            Armor botArmor = EquipmentFactory.createArmor("botArmor", 1, 2);
            Character character = CharacterFactory.createCharacter("vampire", "Dracula_" + i, random.nextInt(200), 150,
                    random.nextInt(20), 0, random.nextInt(400), 0);
            character.setArmor(botArmor);
            character.setWeapon(botWeapon);
            character.setType("vampire");
            user.getPlayer().setCharacter(character);

            users.add(user);
        }

        User adminUser = new User();
        adminUser.setNick("AdminBot");
        adminUser.setPassword("12345");
        adminUser.setName("AdminBot");
        adminUser.setRole("admin");
        Admin newAdmin = new Admin(adminUser.getNick(), adminUser.getPassword(), adminUser.getName());
        adminUser.setAdmin(newAdmin);
        users.add(adminUser);

        saveUsersToFile();
    }

    public static void generateInitialInventories() {
        if (inventories.getWeapons().size() == 0 && inventories.getArmors().size() == 0) {
            return;
        }
        Weapon weapon1 = EquipmentFactory.createWeapon("Longsword", 3, 1, 2);
        Weapon weapon2 = EquipmentFactory.createWeapon("Short Sword", 2, 0, 1);
        Weapon weapon3 = EquipmentFactory.createWeapon("One-handed axe", 2, 1, 1);
        Weapon weapon4 = EquipmentFactory.createWeapon("Axe", 3, 2, 2);
        Weapon weapon5 = EquipmentFactory.createWeapon("Spear", 2, 1, 2);
        inventories.addWeapon(weapon1);
        inventories.addWeapon(weapon2);
        inventories.addWeapon(weapon3);
        inventories.addWeapon(weapon4);
        inventories.addWeapon(weapon5);
        Armor armor1 = EquipmentFactory.createArmor("Bronze Armor", 0, 2);
        Armor armor2 = EquipmentFactory.createArmor("Aluminum Armor", 2, 1);
        Armor armor3 = EquipmentFactory.createArmor("Silver Armor", 1, 2);
        Armor armor4 = EquipmentFactory.createArmor("Gold Armor", 0, 2);
        Armor armor5 = EquipmentFactory.createArmor("Titanium Armor", 2, 3);
        inventories.addArmor(armor1);
        inventories.addArmor(armor2);
        inventories.addArmor(armor3);
        inventories.addArmor(armor4);
        inventories.addArmor(armor5);

        saveInventoriesToFile();
    }

    public static void generateRandomCombates() {
        Random random = new Random();
        ArrayList<User> availableUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getPlayer() != null) {
                availableUsers.add(user);
            }
        }

        Collections.shuffle(availableUsers);
        for (int i = 0; i < availableUsers.size() - 1; i += 2) {
            User challenger = availableUsers.get(i);
            User challenged = availableUsers.get(i + 1);
            int amount = random.nextInt(20) + 1;
            Combate combate = new Combate(challenger, challenged, amount);
            combates.add(combate);
        }
        saveCombatesToFile();
    }

    public static void startInitData() {
        loadUsersFromFile();
        loadInventoriesFromFile();
        loadCombatesFromFile();

        if (users.isEmpty()) {
            generateBots();
        }
        generateInitialInventories();
        if (combates.isEmpty()) {
            generateRandomCombates();
        }

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

    public static Inventory getInventories() {
        return inventories;
    }

    public static ArrayList<Combate> getCombates() {
        return combates;
    }

}