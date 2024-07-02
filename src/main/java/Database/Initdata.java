package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import Character.Character;
import Character.CharacterFactory;
import Character.Equipment.Armor;
import Character.Equipment.EquipmentFactory;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import SystemFunction.Combate;
import User.Admin;
import User.Auth;
import User.Player;
import User.User;

public class Initdata implements Serializable {

    private static final String FILENAME
            = "src\\main\\java\\Database\\DatabaseFile\\users.dat";
    private static final String INVENTORY
            = "src\\main\\java\\Database\\DatabaseFile\\inventory.dat";
    private static final String COMBATES
            = "src\\main\\java\\Database\\DatabaseFile\\combates.dat";
    private static Inventory inventories = new Inventory();
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Combate> combateList = new ArrayList<>();
    private static final SecureRandom random = new SecureRandom();

    public static void startInitData() {
        loadUsersFromFile();
        loadInventoriesFromFile();
        loadCombatesFromFile();

        if (users.isEmpty()) {
            generateBots();
        }
        if (Inventory.getWeapons().isEmpty() && Inventory.getArmors().isEmpty()) {
            generateInitialInventories();
        }

        if (combateList.isEmpty()) {
            generateRandomCombates();
        }

        saveInventoriesToFile();
        saveCombatesToFile();
        saveUsersToFile();
    }

    private static void checkFile(String filePath, String fileType) {
        File file = new File(filePath);
        if (!file.exists()) {
            logFileNotFound(fileType);
            try {
                handleFileCreation(file, fileType);
            } catch (IOException ignored) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadUsersFromFile() {
        checkUsersFile();
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(FILENAME)
                )) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    private static void loadInventoriesFromFile() {
        checkInventoriesFile();
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(INVENTORY)
                )) {
            inventories = (Inventory) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadCombatesFromFile() {
        checkCombatesFile();
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(COMBATES)
                )) {
            combateList = (ArrayList<Combate>) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    public static void saveUsersToFile() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(FILENAME, false)
                )) {
            oos.writeObject(users);
        } catch (IOException ignored) {
        }
    }

    public static void saveInventoriesToFile() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(INVENTORY, false)
                )) {
            oos.writeObject(inventories);
        } catch (IOException ignored) {
        }
    }

    public static void saveCombatesToFile() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(COMBATES, false)
                )) {
            oos.writeObject(combateList);
        } catch (IOException ignored) {
        }
    }

    private static void logFileNotFound(String fileType) {
        System.out.println(fileType + " file not found.");
    }

    private static void handleFileCreation(File file, String fileType)
            throws IOException {
        System.out.println(fileType + " file created");
        file.createNewFile();
    }

    public static void checkUsersFile() {
        checkFile(FILENAME, "User");
    }

    public static void checkInventoriesFile() {
        checkFile(INVENTORY, "Inventory");
    }

    public static void checkCombatesFile() {
        checkFile(COMBATES, "Combates");
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
            String record = Auth.generateRecord();
            Player newPlayer = new Player(user.getNick(), user.getName(), record);
            user.setPlayer(newPlayer);

            Weapon botWeapon = EquipmentFactory.createWeapon("botweapon", 2, 2, 2);
            Armor botArmor = EquipmentFactory.createArmor("botArmor", 1, 2);
            Character character = CharacterFactory.createCharacter(
                    "vampire",
                    "Dracula_" + i,
                    random.nextInt(200),
                    150,
                    random.nextInt(20),
                    0,
                    random.nextInt(400),
                    0
            );
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
        Admin newAdmin = new Admin(
                adminUser.getNick(),
                adminUser.getPassword(),
                adminUser.getName()
        );
        adminUser.setAdmin(newAdmin);
        users.add(adminUser);
    }

    public static void generateInitialInventories() {
        Weapon weapon1 = EquipmentFactory.createWeapon("Longsword", 3, 1, 2);
        Weapon weapon2 = EquipmentFactory.createWeapon("Short Sword", 2, 0, 1);
        Weapon weapon3 = EquipmentFactory.createWeapon("One-handed axe", 2, 1, 1);
        Weapon weapon4 = EquipmentFactory.createWeapon("Axe", 3, 2, 2);
        Weapon weapon5 = EquipmentFactory.createWeapon("Spear", 2, 1, 2);
        Armor armor1 = EquipmentFactory.createArmor("Bronze Armor", 0, 2);
        Armor armor2 = EquipmentFactory.createArmor("Aluminum Armor", 2, 1);
        Armor armor3 = EquipmentFactory.createArmor("Silver Armor", 1, 2);
        Armor armor4 = EquipmentFactory.createArmor("Gold Armor", 0, 2);
        Armor armor5 = EquipmentFactory.createArmor("Titanium Armor", 2, 3);
        inventories.addWeapon(weapon1);
        inventories.addWeapon(weapon2);
        inventories.addWeapon(weapon3);
        inventories.addWeapon(weapon4);
        inventories.addWeapon(weapon5);
        inventories.addArmor(armor1);
        inventories.addArmor(armor2);
        inventories.addArmor(armor3);
        inventories.addArmor(armor4);
        inventories.addArmor(armor5);
    }

    public static void generateRandomCombates() {
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
            combateList.add(combate);
        }
    }

    public static Inventory getInventories() {
        return inventories;
    }

    public static void setInventories(Inventory inventories) {
        Initdata.inventories = inventories;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Initdata.users = users;
    }

    public static ArrayList<Combate> getCombateList() {
        return combateList;
    }

    public static void setCombateList(ArrayList<Combate> combateList) {
        Initdata.combateList = combateList;
    }
}
