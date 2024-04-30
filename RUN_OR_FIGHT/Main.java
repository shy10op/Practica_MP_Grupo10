import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import User.Auth;
//import org.junit.jupiter.api.*;

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
                    user = Auth.login(scanner);
                    if (user != null) {
                        if (user.getAccountStatus()) {
                            break;
                        } else {
                            System.out.println("Your account has been banned.");
                            user = null;
                            continue;
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                        continue;
                    }
                case "2":
                    Auth.signUp(scanner, users, FILENAME);
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
                                        0, 0);
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
                                    continue;
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
                            Menu.inventoryMenu(inventories, scanner, user);
                            Initdata.saveUsersToFile();
                            break;
                        case 4:
                            System.out.println("Let's Challenge");
                            String rivalNick = scanner.nextLine();
                            if (rivalNick.equals(user.getNick())) {
                                System.out.println("You can't challenge yourself.");
                                continue;
                            } else {
                                System.out.println("Enter stake gold");
                                int amount = scanner.nextInt();
                                User chanllenged = User.findUser(rivalNick);
                                Combate combat = new Combate(user, chanllenged, amount);
                                Message.sendCombatToAdmin(combat);
                            }
                            Initdata.saveUsersToFile();
                            break;
                        case 5:
                            System.out.println("Checking for incoming challenges...");
                            Combate combate = Message.receiveCombat(user);
                            if (combate != null) {
                                System.out.println("You have been challenged! Fight amount: " + combate.getAmount());
                                System.out.println("Do you want to fight? (y/n)");
                                String combatOption = scanner.nextLine();
                                if ("y".equals(combatOption)) {
                                    Combate.initialCombat(combate);
                                } else if ("n".equals(combatOption)) {
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

                    System.out.print("Enter your choice: ");
                    int optionAdmin = scanner.nextInt();
                    scanner.nextLine();
                    switch (optionAdmin) {
                        case 1:
                            admin.showPlayers();
                            System.out.println("Enter the nick of the player");
                            String userNick = scanner.nextLine();
                            System.out.println("Searching");
                            User destinationUser = User.findUser(userNick);
                            if (destinationUser != null) {
                                Menu.changeUserMenu(destinationUser, scanner);
                                Menu.changeUserCharacterMenu(destinationUser, scanner);
                                break;
                            } else {
                                System.out.println("Invalid option. Please try again.");
                                continue;
                            }
                        case 2:
                            Menu.inventoryMenu(inventories, scanner, user);
                            break;
                        case 3:
                            Menu.combatListMenu();
                            System.out.print(
                                    "Enter the nickname of the Challenger or Challenged to view details (exit): ");
                            String combatnick = scanner.nextLine();
                            Combate selectedCombate = Menu.combatMenu(combatnick);
                            if (selectedCombate == null || combatnick.equals("exit")) {
                                System.out.println("Invalid combate. Please try again.");
                                continue;
                            }
                            System.out.print("Do you want to validate this combat? (y/n): ");
                            String validate = scanner.nextLine().trim().toLowerCase();
                            if (validate.equals("y")) {
                                Message.sendCombatToChallenged(selectedCombate);
                                System.out.println("The combat has been sent to the challenged: "
                                        + selectedCombate.getChallenged().getNick());
                                Combate.deleteCombate(selectedCombate);
                            } else {
                                Combate.deleteCombate(selectedCombate);
                            }
                            System.out.println("\n");
                            Initdata.saveCombatesToFile();
                            break;
                        case 4:
                            System.out.print("List of players nick: \n");
                            ArrayList<User> PlayerList = Admin.getPlayers();
                            int i = 0;
                            for (User userPlayer : PlayerList) {
                                if (userPlayer.getAccountStatus()) {
                                    System.out.printf("User %d : %s \n", i, userPlayer.getNick());
                                    i = i + 1;
                                }
                            }
                            System.out.println("Enter the nick of the player who will be banned (exit)");
                            String banned = scanner.nextLine();
                            if (banned.equals("exit")) {
                                break;
                            } else {
                                Admin.banUser(banned);
                                Initdata.saveUsersToFile();
                                continue;
                            }
                        case 5:
                            System.out.print("BanList: \n");
                            ArrayList<User> banList = Admin.getPlayers();
                            Iterator<User> iterator = banList.iterator();

                            int j = 0;

                            while (iterator.hasNext()) {
                                User bannedUser = iterator.next();
                                if (!bannedUser.getAccountStatus()) {
                                    System.out.printf("User %d : %s \n", j, bannedUser.getNick());
                                    j++;
                                } else {
                                    iterator.remove();
                                }
                            }

                            if (banList.isEmpty()) {
                                System.out.println("There are no banned players");
                                break;
                            }

                            System.out.println("Enter the nick of the player who will be unbanned (exit)");
                            String unBanned = scanner.nextLine();
                            if (unBanned.equals("exit")) {
                                break;
                            } else {
                                Admin.unBanUser(unBanned);
                                Initdata.saveUsersToFile();
                                break;
                            }

                        case 6:
                            System.out.println("Exiting the program...");
                            user.setLogged(false);
                            break;
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
