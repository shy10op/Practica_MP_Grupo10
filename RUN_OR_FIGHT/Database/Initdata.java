package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import User.User;

public class Initdata {
    private static final String FILENAME = "users.dat";
    private static ArrayList<User> users = new ArrayList<>();

    @SuppressWarnings("unchecked")
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

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void generateBots() {
        // Create random users
    }

    public void startInitData() {
        checkUsersFile();
        generateBots();
    }

    public static String getFILENAME() {
        return FILENAME;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
