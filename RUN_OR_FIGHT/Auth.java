import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import User.User;
import User.Admin;
import User.Player;
import User.RecordPlayer;

public class Auth {

    public static User login(Scanner scanner, List<User> users) throws IOException {
        System.out.print("Enter nick: ");
        String nick = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        for (User user : users) {
            if (user.getNick().equals(nick) && user.getPassword().equals(password)) {
                System.out.println("Welcome, " + user.getName() + "!");
                user.setLogged(true);
                return user;
            }
        }
        return null;
    }

    public static void signUp(Scanner scanner, List<User> users, String FILENAME) {
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

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing users to file: " + e.getMessage());
        }

    }
}