import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import User.User;
import Database.Initdata;
import SystemFunction.Menu;

public class Main {

    public static void main(String[] args) throws IOException {

        Initdata initData = new Initdata();
        String FILENAME = Initdata.getFILENAME();
        ArrayList<User> users = Initdata.getUsers();
        initData.startInitData();

        Scanner scanner = new Scanner(System.in);
        User user = new User();
        boolean exit = false; // Variable para controlar la salida del bucle

        while (!exit) {
            Menu.authMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    user = Auth.login(scanner, users);
                    if (user != null) {
                        System.out.println("Welcome, " + user.getName() + "!");
                        user.setLogged(true);

                        while (user.isLogged()) {
                            String userRole = user.getRole();
                            if (userRole.equals("admin")){
                                Menu.adminMenu();
                                user.setLogged(false);
                            }else if (userRole.equals("user")){
                                Menu.playerMenu(); 
                                user.setLogged(false);
                            }
                        }
                        exit = true;
                        // despues de esto entra al menu principal
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    Auth.signUp(scanner, users, FILENAME);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
