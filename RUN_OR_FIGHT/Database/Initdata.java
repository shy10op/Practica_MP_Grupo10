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
import Character.Equipment.*;

public class Initdata implements Serializable {
    private static final String FILENAME = "users.dat";
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

    @SuppressWarnings("unchecked")
    public static void loadUsersFromFile() {
        checkUsersFile(); // Asegúrate de que el archivo existe o es creado.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            users = (ArrayList<User>) ois.readObject();
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
            users.add(user);
        }
        saveUsersToFile();
    }

    public static void startInitData() {
        loadUsersFromFile();
        generateBots();
        InitSaveInventory();
    }

    public static String getFILENAME() {
        return FILENAME;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    private static void InitSaveInventory() {
        String path = "inventario.dat";
        File fichero = new File(path);
        Inventory inventory;

        // Verificar si el fichero existe
        if (!fichero.exists()) {
            // Si el fichero no existe, inicializar y guardar un nuevo inventario
            inventory = new Inventory();
            InventoryInitializer.initializeGame(inventory);
            InventoryInitializer.SaveInventory(inventory, path);
        } else {
            // Si el fichero ya existe, cargar el inventario existente
            inventory = InventoryInitializer.LoadInventory(path);
        }
    }

}