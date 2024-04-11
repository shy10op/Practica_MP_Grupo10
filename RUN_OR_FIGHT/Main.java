import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Character.CharacterFactory;
import Character.Character;
import User.Player;
import User.Admin;
import User.AdminTools;
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
                    for (User user2 : users) {
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

                if (user.getCharacter() == null) {
                    Menu.chooseMenu();
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    Character character = null;
                    switch (choice) {
                        case 1:
                            character = CharacterFactory.createCharacter("hunter", "Van Helsing", 100, 100, 20, 0,
                                    0,
                                    0);
                            user.setCharacter(character);
                            break;
                        case 2:
                            character = CharacterFactory.createCharacter("vampire", "Dracula", 200, 150, 30, 5, 400,
                                    0);
                            user.setCharacter(character);
                            break;
                        case 3:
                            character = CharacterFactory.createCharacter("werewolf", "Wolf", 150, 120, 25, 0, 0, 1);
                            user.setCharacter(character);
                            break;
                        case 4:
                            System.out.println("Exiting the program...");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice, please try again.");
                            continue;
                    }
                }

                Menu.playerMenu();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        System.out.println(user.getCharacter().getName());
                        break;
                    case 2:
                        // UnenrollCharacter();
                    case 3:
                        // modify Item Menu
                    case 4:
                        System.out.println("Let's Challenge");
                        String rivalNick = scanner.nextLine();
                        Message.sendCombat(user.getNick(), rivalNick);
                        System.out.println("You: " + user.getNick() + " vs Rival: " + rivalNick);
                        break;
                    case 5:
                        System.out.println("Mensaje");
                        User rival = Message.receiveCombat(user);

                        // esto es para comprobar
                        if (rival != null) {
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
                        // secret test case 8
                    case 8:
                        Message.sendCombat("Bot2", "Bot1");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }

            } else if (userRole.equals("admin")) {
                Menu.adminMenu();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        List<Player> players = admin.getPlayerList(); // Obtiene la lista de jugadores
                        admin.showPlayers(); // Muestra los jugadores

                        System.out.println("Introduce el apodo del jugador para buscar:");
                        String nick = scanner.nextLine(); // Lee el apodo por teclado
                        Player player = admin.findPlayerByNick(nick); // Encuentra al jugador por su apodo

                        if (player != null) {
                            System.out.println("Introduce el nombre del atributo a modificar:");
                            String attributeName = scanner.nextLine(); // Lee el nombre del atributo por teclado
                            System.out.println("Introduce el nuevo valor para el atributo:");
                            String newValue = scanner.nextLine(); // Lee el nuevo valor por teclado

                            admin.modifyCharacterAttributes(nick, attributeName, newValue); // Modifica los atributos
                                                                                            // del personaje
                        } else {
                            System.out.println("Jugador no encontrado.");
                        }

                        admin.updatePlayerList(); // Actualiza la lista de jugadores
                        break;

                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        System.out.println("Running away");
                        user.setLogged(false);
                }

            }
        }

        scanner.close();
        Initdata.saveUsersToFile();
    }
}
