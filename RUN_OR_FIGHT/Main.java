import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Character.CharacterFactory;
import Character.Character;
import User.Player;
import User.User;
import Character.Equipment.*;
import Character.Minions.Minion;
import Character.Minions.MinionFactory;
import Character.Minions.MinionEspecific.Human.Loyalty;
import Database.Initdata;
import SystemFunction.Combate;
import SystemFunction.Menu;
import SystemFunction.Message;
import User.Admin;

public class Main {

    public static void main(String[] args) throws IOException {

        String FILENAME = Initdata.getFILENAME();
        Initdata.startInitData();
        ArrayList<User> users = Initdata.getUsers();
        ArrayList<Inventory> inventories = Initdata.getInventories();

        Scanner scanner = new Scanner(System.in);
        User user = new User();
        boolean exit = false;
        Random rand = new Random();

        while (!exit) {
            Menu.authMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    user = Auth.login(scanner, users);
                    if (user != null) {
                        System.out.println("Welcome, " + user.getName() + "!");
                        break;
                    } else {
                        System.out.println("Invalid username or password.");
                        continue;
                    }
                case "2":
                    Auth.signUp(scanner, users, FILENAME);
                    // user.setLogged(false);
                    break;
                case "3":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            while (user.isLogged()) {
                String userRole = user.getRole();
                Player player = user.getPlayer();

                if (userRole.equals("player")) {
                    if (player.getCharacter() == null) {
                        Menu.chooseCharacterMenu();
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        Character character = null;
                        switch (choice) {
                            case 1:
                                character = CharacterFactory.createCharacter("hunter", "Van Helsing", 100, 100, 15, 0,
                                        0,
                                        0);
                                character.setType("hunter");
                                player.setCharacter(character);
                                Initdata.saveUsersToFile();
                                break;
                            case 2:
                                character = CharacterFactory.createCharacter("vampire", "Dracula", 200, 150, 10, 5, 400,
                                        0);
                                character.setType("vampire");
                                player.setCharacter(character);
                                Initdata.saveUsersToFile();
                                break;
                            case 3:
                                character = CharacterFactory.createCharacter("werewolf", "Wolf", 150, 120, 20, 0, 0, 3);
                                character.setType("werewolf");
                                player.setCharacter(character);
                                Initdata.saveUsersToFile();
                                break;
                            case 4:
                                user.setLogged(false);
                                break;
                            default:
                                System.out.println("Invalid choice, please try again.");
                                continue;
                        }
                    }

                    if (player.getCharacter().getMinion() == null) {
                        Menu.chooseMinionMenu();
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        Minion minion = null;
                        switch (choice) {
                            case 1:
                                minion = MinionFactory.createMinion("human", "humanKnight", rand.nextInt(40),
                                        Loyalty.NORMAL,
                                        false, null, 0);
                                minion.setType("human");
                                if (player.getCharacter().getType().equals("vampire")) {
                                    System.out.println("Vampire cant have human minions");
                                } else {
                                    player.getCharacter().setMinions(minion);
                                }
                                Initdata.saveUsersToFile();
                                break;
                            case 2:
                                String description = "this is the description about the demon";
                                minion = MinionFactory.createMinion("demon", "demonKnight", rand.nextInt(40),
                                        Loyalty.NORMAL,
                                        true, description, 0);
                                minion.setType("demon");
                                player.getCharacter().setMinions(minion);
                                Initdata.saveUsersToFile();
                                break;
                            case 3:
                                minion = MinionFactory.createMinion("ghoul", "ghoulKnight", rand.nextInt(40),
                                        Loyalty.NORMAL,
                                        false, null, 4);
                                minion.setType("ghoul");
                                player.getCharacter().setMinions(minion);
                                Initdata.saveUsersToFile();
                                break;
                            case 4:
                                user.setLogged(false);
                                break;
                            default:
                                System.out.println("Invalid choice, please try again.");
                                continue;
                        }

                    }

                    Menu.playerMenu();
                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1:
                            Menu.userMenu(user);
                            break;
                        case 2:
                            System.out.println("Are you sure you want to delete your account? (yes/no)");
                            String confirmation = scanner.nextLine();
                            if ("yes".equalsIgnoreCase(confirmation)) {
                                User.deleteUser(user.getNick());
                                System.out.println("Your account has been successfully deleted.");
                            } else {
                                System.out.println("Account deletion canceled.");
                            }
                            break;
                        case 3:
                            Menu.inventoryMenu(inventories, scanner, player);
                            Initdata.saveUsersToFile();
                            break;
                        case 4:
                            System.out.println("Let's Challenge");
                            String rivalNick = scanner.nextLine();
                            System.out.println("Enter stake gold");
                            int amount = scanner.nextInt();
                            User chanllenged = User.findUser(rivalNick);
                            Combate combat = new Combate(user, chanllenged, amount);

                            Message.sendCombat(combat);
                            System.out.println("You: " + user.getNick() + " vs Rival: " + rivalNick);
                            break;
                        case 5:
                            System.out.println("Checking for incoming challenges...");
                            Combate combate = Message.receiveCombat(user);
                            if (combate != null) {
                                System.out.println("You have been challenged! Fight amount: " + combate.getAmount());
                                System.out.println("Do you want to fight? (yes/no)");
                                String combatOption = scanner.nextLine();
                                if ("yes".equals(combatOption)) {
                                    Combate.initialCombat(combate);
                                } else if ("no".equals(combatOption)) {
                                    Character actualCharacter = user.getPlayer().getCharacter();
                                    int result = actualCharacter.getGold() - combate.getAmount();
                                    System.out.println("Your current gold after declining the challenge is: " + result);
                                    actualCharacter.setGold(result);
                                    System.out.println("You have chosen not to fight.");
                                }
                                Initdata.saveUsersToFile();
                            } else {
                                System.out.println("No challenges at the moment.");
                            }
                            break;
                        case 6:
                            Menu.rankingMenu(users);
                            break;
                        case 7:
                            System.out.println("Running away");
                            user.setLogged(false);
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }

                } else if (userRole.equals("admin")) {
                    Admin admin = user.getAdmin();
                    Menu.adminMenu();
                    admin.showPlayers();
                    System.out.print("Enter your choice: ");
                    int optionAdmin = scanner.nextInt();
                    scanner.nextLine();
                    switch (optionAdmin) {
                        case 1:
                            System.out.println("About to show players");

                            admin.showPlayers();
                            System.out.println("Enter the nick of the player");
                            String userNick = scanner.nextLine();
                            System.out.println("Searching");
                            User destinationUser = User.findUser(userNick);
                            if (destinationUser != null) {

                                System.out.println("Enter the changed value");
                                String changed = scanner.nextLine();
                                System.out.println(("Enter the new value"));
                                String newValue = scanner.nextLine();

                                // Aqui pasar la funcion modifyCharacterAttributes
                                admin.modifyCharacterAttributes(userNick, changed, newValue);
                            } else {
                                System.out.println("Invalid option. Please try again.");
                            }
                            break;
                        case 2:

                        case 3:

                        case 4:
                            System.out.print("List of players nicknames:");
                            ArrayList<User> listPlayers = Admin.getPlayers();
                            int i = 0;
                            for (User userPlayer : listPlayers) {
                                i = i + 1;
                                System.out.println("User Nick " + i + " : " + userPlayer.getNick());
                            }
                            System.out.println("Enter the nick of the player who will be banned");
                            String banned = scanner.nextLine();
                            Admin.banUser(banned);
                            System.out.println("Player banned successfully");
                        case 5:

                        case 6:
                            System.out.println("Exiting the program...");
                            user.setLogged(false);
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                }
            }
        }
        scanner.close();
        Initdata.saveUsersToFile();

    }
}
