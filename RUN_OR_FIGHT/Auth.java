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
                System.out.println("Login successful. Welcome, " + user.getName() + "!");
                user.setLogged(true);
                return user;
            }
        }
        return null;
    }

    /**
     * @param scanner
     * @param users
     * @param FILENAME
     */
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
        boolean role = false;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Do you want to be an admin? (yes/no)");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                role = Boolean.parseBoolean(input);
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }

        if (nick.isEmpty() || password.isEmpty() || name.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        User newUser = new User(nick, password, name, role);
        if (role) {
            newUser = new Admin(nick, password, name);
            System.out.println("Registration successful. Welcome Admin, " + name + "!");
        } else {
            newUser = new Player(nick, password, name, RecordPlayer.generateRecord());
            System.out.println("Registration successful. Welcome player, " + name + "!");
            System.out.println(RecordPlayer.generateRecord());
        }
        users.add(newUser);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error writing users to file: " + e.getMessage());
        }
    }
}