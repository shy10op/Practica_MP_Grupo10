package User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Database.Initdata;

public class Auth {
    private static ArrayList<User> users = Initdata.getUsers();

    public static User login(Scanner scanner) throws IOException {
        System.out.print("Enter nick: ");
        String nick = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        return loginAuth(nick, password);
    }

    public static User loginAuth(String nick, String password) {
        for (User user : users) {
            if (user.getNick().equals(nick) && user.getPassword().equals(password)) {
                System.out.println("Welcome, " + user.getName() + "!");
                user.setLogged(true);
                return user;
            }
        }
        return null;
    }

    public static void signUp(Scanner scanner) {
        System.out.print("Enter nick: ");
        String nick = scanner.nextLine().trim();

        String password;
        while (true) {
            System.out.print("Enter password (8-12 characters): ");
            password = scanner.nextLine().trim();
            if (password.length() >= 8 && password.length() <= 12) {
                break;
            } else {
                System.out.println("Password must be between 8 and 12 characters long");
            }
        }
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        String role = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Selecte a Role (admin/player)");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("admin") || input.equalsIgnoreCase("player")) {
                role = input;
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter admin or player.");
            }
        }

        if (nick.isEmpty() || password.isEmpty() || name.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        User newUser = new User(nick, password, name, role);
        if ("admin".equalsIgnoreCase(newUser.getRole())) {
            Admin newAdmin = new Admin(nick, password, role);
            newUser.setAdmin(newAdmin);
            System.out.println("Registration successful. Welcome Admin, " + name + "!");
        } else if ("player".equalsIgnoreCase(newUser.getRole())) {
            Player newPlayer = new Player(nick, name, RecordPlayer.generateRecord());
            newUser.setPlayer(newPlayer);
            System.out.println("Registration successful. Welcome player, " + name + "!");
            System.out.println(RecordPlayer.generateRecord());
        } else {
            System.out.println("Error: " + newUser.getRole());
        }
        users.add(newUser);
        Initdata.saveUsersToFile();
    }
}
