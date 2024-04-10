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
            return;
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadUsersFromFile() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
                users = (ArrayList<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateBots() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNick("Bot" + i);
            user.setPassword("12345");
            // user.setRole("user");
            user.setName("BotName" + i);
            // para otras cosas de usuarios añadirlas aqui
            // character

            users.add(user);
        }
        saveUsersToFile(); // Guarda los bots en el archivo
    }

    public void startInitData() {
        checkUsersFile();
        loadUsersFromFile();
        generateBots();
    }

    public static String getFILENAME() {
        return FILENAME;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}