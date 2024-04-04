import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import User.User;
import Database.Initdata;

public class Main {

    public static void main(String[] args) throws IOException {
        Initdata initData = new Initdata();
        initData.startInitData();
        List<User> users = Initdata.getUsers();
        String FILENAME = Initdata.getFILENAME();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false; // Variable para controlar la salida del bucle

        while (!exit) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    User user = Auth.login(scanner, users);
                    if (user != null) {
                        System.out.println("Welcome, " + user.getName() + "!");
                        // user.setLogged(true);
                        // despues de esto entra al menu principal
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    Auth.signUp(scanner, users, FILENAME);
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
}
