import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import User.User;

public class Main {
    private static final String FILENAME = "users.dat";
    private static ArrayList<User> users = new ArrayList<>();
    private static boolean isLogged = false;

    public static void main(String[] args) {
        loadUsers();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit && !isLogged) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    signUp(scanner);
                    saveUsers();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter nick: ");
        String nick = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getNick().equals(nick) && user.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, " + user.getName() + "!");
                isLogged = true;
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter nick: ");
        String nick = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        if (nick.isEmpty() || password.isEmpty() || name.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        User newUser = new User(nick, password, name);
        users.add(newUser);
        System.out.println("Registration successful. Welcome, " + name + "!");
    }

    @SuppressWarnings("unchecked")
    private static void loadUsers() {
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

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
