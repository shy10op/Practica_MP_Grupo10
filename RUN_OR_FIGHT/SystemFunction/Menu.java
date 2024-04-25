package SystemFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Character.Character;
import Character.CharacterFactory;
import Character.Equipment.Armor;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import Database.Initdata;
import User.Admin;
import User.Player;
import User.User;

public class Menu {

    public static void printHeader(String title) {
        String header = "+-------------------------------------------------+";
        System.out.println(header);
        System.out.printf("| %-48s|\n", title);
        System.out.println(header);
    }

    public static void playerMenu() {
        printHeader("Main Menu");
        System.out.println("| 1. View Character                               |");
        System.out.println("| 2. Delete Character                             |");
        System.out.println("| 3. Modify Active Items                          |");
        System.out.println("| 4. Challenge another user                       |");
        System.out.println("| 5. Message                                      |");
        System.out.println("| 6. Check World Ranking                          |");
        System.out.println("| 7. Exit                                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.print("Choose an option: ");
    }

    public static void adminMenu() {
        printHeader("Admin Menu");
        System.out.println("| 1. Edit Character                               |");
        System.out.println("| 2. Add Items To The Character                   |");
        System.out.println("| 3. Validate Challenges                          |");
        System.out.println("| 4. Block Players                                |");
        System.out.println("| 5. Unlock Players                               |");
        System.out.println("| 6. Exit                                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.print("Choose an option: ");
    }

    public static void changeUserMenu(User destinationUser, Scanner scanner) {
        Menu.printHeader("User");
        System.out.printf("UserNick: %s To ", destinationUser.getNick());
        String newNick = scanner.nextLine();
        destinationUser.setNick(newNick);

        System.out.printf("UserName: %s To ", destinationUser.getName());
        String newName = scanner.nextLine();
        destinationUser.setName(newName);

        Initdata.saveUsersToFile();
    }

    public static void changeUserCharacterMenu(User destinationUser, Scanner scanner) {
        Menu.printHeader("User Character");
        Character destinationCharacter = destinationUser.getPlayer().getCharacter();
        System.out.printf("Character Name: %s  To ", destinationCharacter.getName());
        String newName = scanner.nextLine();
        destinationCharacter.setName(newName);

        System.out.printf("Character Health : %d  To ", destinationCharacter.getHealth());
        int newHealth = scanner.nextInt();
        destinationCharacter.setHealth(newHealth);

        System.out.printf("Character Power : %d  To ", destinationCharacter.getPower());
        int newPower = scanner.nextInt();
        destinationCharacter.setPower(newPower);

        System.out.printf("Character Gold: %d  To ", destinationCharacter.getGold());
        int newGold = scanner.nextInt();
        destinationCharacter.setGold(newGold);

        System.out.printf("Character type : %s To (hunter,vampire,werewolf)",
                destinationCharacter.getType());
        String newType = scanner.next();
        destinationCharacter.setType(newType);

        int newAge = 0;
        int newBlood = 0;
        int newRage = 0;
        if (newType.equals("vampire")) {
            System.out.printf("Set a new Age: ");
            newAge = scanner.nextInt();
            System.out.printf("Set a new Blood: ");
            newBlood = scanner.nextInt();
        }

        if (newType.equals("werewolf")) {
            System.out.printf("Set a new Rage: ");
            newRage = scanner.nextInt();
        }

        Character newCharacter = CharacterFactory.createCharacter(newType, newName, newGold,
                newHealth, newPower, newBlood, newAge, newRage);
        destinationUser.getPlayer().setCharacter(newCharacter);
        System.out.println("User changed successfully");

        Initdata.saveUsersToFile();
    }

    public static void combatListMenu() {
        printHeader("Combat List");
        ArrayList<Combate> combates = Initdata.getCombates();
        if (combates.isEmpty()) {
            System.out.println("No hay combates en este momento.");
            return;
        }

        System.out.println("+-------------------------------------------------+");
        System.out.println("| Challenger VS Challenged                        |");
        System.out.println("+-------------------------------------------------+");
        for (Combate combate : combates) {
            String challengerName = combate.getChallenger().getNick();
            String challengedName = combate.getChallenged().getNick();
            System.out.printf("| %s VS %s" + "  Gold: %d \n", challengerName, challengedName,
                    combate.getAmount());
        }
        System.out.println("+-------------------------------------------------+");
        System.out.print("\n");
    }

    public static Combate combatMenu(String nick) {
        printHeader("Combat found involving " + nick + ":");
        ArrayList<Combate> combates = Initdata.getCombates();
        boolean found = false;
        for (Combate combate : combates) {
            if (combate.getChallenger().getNick().equals(nick) ||
                    combate.getChallenged().getNick().equals(nick)) {
                Character challenger = combate.getChallenger().getPlayer().getCharacter();
                Character challenged = combate.getChallenged().getPlayer().getCharacter();
                printHeader("Challenger: ");
                printCharacterInfo(challenger);
                printHeader("Challenged: ");
                printCharacterInfo(challenged);

                System.out.println("+-------------------------------------------------+");
                System.out.printf("| Stake: %d\n", combate.getAmount());
                System.out.println("+-------------------------------------------------+");
                found = true;
                return combate;
            }
        }

        if (!found) {
            System.out.println("No combats found for " + nick);
        }
        return null;
    }

    private static void printCharacterInfo(Character character) {
        System.out.printf("| Name: %s, Type: %s, Health: %d, Power: %d, Gold: %d\n",
                character.getName(), character.getType(), character.getHealth(),
                character.getPower(), character.getGold());
        if (character.getWeapon() != null) {
            System.out.println("| Weapon: " + character.getWeapon().getName()
                    + "  Attack Mod: " + character.getWeapon().getModAttack()
                    + "  Defense Mod: " + character.getWeapon().getModDefense());
        } else {
            System.out.println("| Weapon: None");
        }
        if (character.getArmor() != null) {
            System.out.println("| Armor: " + character.getArmor().getName()
                    + "  Attack Mod: " + character.getArmor().getModAttack()
                    + "  Defense Mod: " + character.getArmor().getModDefense());
        } else {
            System.out.println("| Armor: None");
        }
        if (character.getMinion() != null) {
            System.out.println(
                    "| Minion: " + character.getMinion().getName() + "  Type: " + character.getMinion().getType()
                            + "  Health: " + character.getMinion().getHealth());
        } else {
            System.out.println("| Minion: None");
        }
    }

    public static void authMenu() {
        printHeader("Auth Menu");
        System.out.println("| 1. Login                                        |");
        System.out.println("| 2. Register                                     |");
        System.out.println("| 3. Exit                                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.print("Choose an option: ");
    }

    public static void chooseCharacterMenu() {
        printHeader("Choose a Character");
        System.out.println("| 1. Hunter                                       |");
        System.out.println("| 2. Vampire                                      |");
        System.out.println("| 3. Werewolf                                     |");
        System.out.println("| 4. Exit                                         |");
        System.out.println("+-------------------------------------------------+");
    }

    public static void chooseMinionMenu() {
        printHeader("Choose a Minion");
        System.out.println("| 1. Human                                        |");
        System.out.println("| 2. Demon                                        |");
        System.out.println("| 3. Ghoul                                        |");
        System.out.println("| 4. Exit                                         |");
        System.out.println("+-------------------------------------------------+");
    }

    public static void AdminOption1() {
        printHeader("Change Feature");
        System.out.println("| 1. Name                                         |");
        System.out.println("| 2. Power                                        |");
        System.out.println("| 3. Health                                       |");
        System.out.println("| 4. Exit                                         |");
        System.out.println("+-------------------------------------------------+");
    }

    public static void userMenu(User user) {
        printHeader("User Info");
        System.out.printf("| Nickname: %-37s |\n", user.getNick());
        System.out.printf("| Name: %-41s |\n", user.getName());
        System.out.printf("| Role: %-41s |\n", user.getRole());

        Player player = user.getPlayer();
        if (player != null) {
            Character character = player.getCharacter();
            printHeader("Player Info");
            System.out.printf("| Record: %-38s \n", player.getRecord());
            System.out.printf("| Name: %-38s \n", character.getName());
            System.out.printf("| Health: %-36s \n", character.getHealth());
            System.out.printf("| Gold: %-38s \n", character.getGold());
            System.out.printf("| Power: %-38s \n", character.getPower());
            if (character.getMinion() != null) {
                System.out.printf("| Minion: %-38s \n", character.getMinion().getType());
            }
            if (character.getArmor() != null) {
                System.out.printf("| Armor: %-38s \n", character.getArmor().getName());
            }
            if (character.getWeapon() != null) {
                System.out.printf("| Weapon: %-38s \n", character.getWeapon().getName());
            }
        }

        Admin admin = user.getAdmin();
        if (admin != null) {
            System.out.println("| Admin Info:                                     |");
            System.out.println("+-------------------------------------------------+");
        }

        System.out.println("\n");
    }

    public static void rankingMenu(ArrayList<User> users) {
        printHeader("Ranking");
        List<User> filteredSortedUsers = users.stream()
                .filter(u -> u.getPlayer() != null && u.getPlayer().getCharacter() != null)
                .sorted((u1, u2) -> Integer.compare(u2.getPlayer().getCharacter().getGold(),
                        u1.getPlayer().getCharacter().getGold()))
                .collect(Collectors.toList());

        int rank = 1;
        for (User user : filteredSortedUsers) {
            if (rank > 20)
                break;
            if (user.getAccountStatus()) {
                int gold = user.getPlayer().getCharacter().getGold();
                System.out.printf("| %d. %-20s Gold: %d \n", rank, user.getName(), gold);
                rank++;
            }
        }
        System.out.println("\n");
    }

    public static void inventoryMenu(ArrayList<Inventory> inventories, Scanner scanner, Player player) {
        Character character = player.getCharacter();
        printHeader("Inventory");

        for (Inventory inventory : inventories) {
            // Weapons
            System.out.println("| Weapons:                                        |");
            if (!inventory.getWeapons().isEmpty()) {
                for (Weapon weapon : inventory.getWeapons()) {
                    System.out.printf("| %-47s |\n", weapon.getName() + " Attack Mod: " + weapon.getModAttack()
                            + " Defense Mod: " + weapon.getModDefense() + " Hand Space: " + weapon.getSpaceHand());
                }
            } else {
                System.out.println("|   No weapons in inventory.                      |");
            }

            // Armors
            System.out.println("| Armors:                                         |");
            if (!inventory.getArmors().isEmpty()) {
                for (Armor armor : inventory.getArmors()) {
                    System.out.printf("| %-47s |\n", armor.getName() + " Attack Mod: " + armor.getModAttack()
                            + " Defense Mod: " + armor.getModDefense());
                }
            } else {
                System.out.println("|   No armors in inventory.                       |");
            }

            System.out.println("+-------------------------------------------------+");
            System.out.println("Enter the name of the weapon you'd like to choose:");
            String weaponName = scanner.nextLine();
            Weapon chosenWeapon = inventory.getWeapons().stream()
                    .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                    .findFirst()
                    .orElse(null);

            if (chosenWeapon != null) {
                character.setWeapon(chosenWeapon);
                System.out.println("You have chosen: " + chosenWeapon.getName());
            } else {
                System.out.println("Weapon not found.");
            }

            System.out.println("Enter the name of the armor you'd like to choose:");
            String armorName = scanner.nextLine();
            Armor chosenArmor = inventory.getArmors().stream()
                    .filter(armor -> armor.getName().equalsIgnoreCase(armorName))
                    .findFirst()
                    .orElse(null);

            if (chosenArmor != null) {
                character.setArmor(chosenArmor);
                System.out.println("You have chosen: " + chosenArmor.getName());
            } else {
                System.out.println("Armor not found.");
            }
        }
        Initdata.saveUsersToFile();
        System.out.println("\n");
    }
}
