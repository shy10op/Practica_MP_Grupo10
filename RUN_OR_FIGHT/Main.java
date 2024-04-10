import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import User.User;
import Database.Initdata;
import SystemFunction.Menu;
import SystemFunction.Message;

public class Main {

    public static void main(String[] args) throws IOException {

        String FILENAME = Initdata.getFILENAME();
        Initdata.startInitData();
        ArrayList<User> users = Initdata.getUsers();

        Scanner scanner = new Scanner(System.in);
        User user = new User();
        boolean exit = false;
        while (!exit) {
            Menu.authMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    user = Auth.login(scanner, users);
                    if (user != null) {
                        System.out.println("Welcome, " + user.getName() + "!");
                        exit = true;
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
                case 4:
                    for (User user2: users) {
                        System.out.println("username: " + user2.getName());
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        while (user.isLogged()) {
            String userRole = user.getRole();
            if (userRole.equals("player")) {
                Menu.playerMenu();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        // RegisterCharacter();
                        break;
                    case 2:
                        //UnenrollCharacter();
                    case 3:
                        //modify Item Menu
                    case 4:
                        System.out.println("Let's Challenge");
                        String rivalNick = scanner.nextLine();
                        Message.sendCombat(user.getNick(), rivalNick);
                        System.out.println("You: " + user.getNick() + " vs Rival: " + rivalNick);
                        break;
                    case 5:
                        System.out.println("Mensaje");
                        User rival = Message.receiveCombat(user);

                        //esto es para comprobar
                        if (rival!= null) {
                            System.out.println(user.getNick() + " vs " + rival.getNick());
                        } else {
                            System.out.println("No user found");
                        }

                        System.out.println("U want to fight? (yes/no)");
                        String combatOption = scanner.nextLine();
                        if (combatOption.equals("yes")) {
                            System.out.println("Fighting");
                        } else {
                            System.out.println("Not fighting");
                        }
                    
                    case 6:
                        // check Ranking
                        break;
                    case 7:// exit
                        System.out.println("Running away");
                        user.setLogged(false);

                    //secret test case 8
                    case 8:
                        Message.sendCombat("Bot2", "Bot1");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
                user.setLogged(false);
            } else if (userRole.equals("admin")) {
                Menu.adminMenu();
                user.setLogged(false);
            }
        }

        scanner.close();
        Initdata.saveUsersToFile();
    }
}
