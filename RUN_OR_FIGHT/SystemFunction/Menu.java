package SystemFunction;

import java.util.ArrayList;
import java.util.Scanner;

import Character.Character;
import Character.Equipment.Armor;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import User.Admin;
import User.Player;
import User.User;

public class Menu {

    public static void playerMenu() {
        System.out.println("+-------------------Main Menu-------------------+");
        System.out.println("| 1. View Character                             |");
        System.out.println("| 2. Delete Character                           |");
        System.out.println("| 3. Modify Active Items                        |");
        System.out.println("| 4. Challenge another user                     |");
        System.out.println("| 5. Message                                    |");
        System.out.println("| 6. Check World Ranking                        |");
        System.out.println("| 7. Exit                                       |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("Choose an option: ");
    }

    public static void adminMenu() {
        System.out.println("+-------------------Admin Menu------------------+");
        System.out.println("| 1. Edit Character                             |");
        System.out.println("| 2. Add Items To The Character                 |");
        System.out.println("| 3. Validate Challenges                        |");
        System.out.println("| 4. Block Players                              |");
        System.out.println("| 5. Unlock Players                             |");
        System.out.println("| 6. Exit                                       |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("Choose an option: ");
    }

    public static void authMenu() {
        System.out.println("+-------------------Auth Menu-------------------+");
        System.out.println("| 1. Login                                      |");
        System.out.println("| 2. Register                                   |");
        System.out.println("| 3. Exit                                       |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("Choose an option: ");
    }

    public static void chooseMenu() {
        System.out.println("+----------------Choose a character-------------+");
        System.out.println("| 1. Hunter                                     |");
        System.out.println("| 2. Vampire                                    |");
        System.out.println("| 3. Werewolf                                   |");
        System.out.println("| 4. Exit                                       |");
        System.out.println("+-----------------------------------------------+");
    }

    public static void AdminOpcion1() {
        System.out.println("+------------------Change Feature---------------+");
        System.out.println("| 1. Name                                       |");
        System.out.println("| 2. Power                                      |");
        System.out.println("| 3. Health                                     |");
        System.out.println("| 4. Exit                                       |");
        System.out.println("+-----------------------------------------------+");
    }

    public static void userMenu(User user) {
        System.out.println("+-------------------------------------------------+");
        System.out.println("|                    User Info                    |");
        System.out.println("+-------------------------------------------------+");
        System.out.printf("| Nickname: %-37s |\n", user.getNick());
        System.out.printf("| Name: %-41s |\n", user.getName());
        System.out.printf("| Role: %-41s |\n", user.getRole());

        // Condicionales para mostrar información del jugador o admin si están presentes

        Player player = user.getPlayer();
        if (player != null) {
            Character character = player.getCharacter();
            System.out.println("+-------------------------------------------------+");
            System.out.println("|                    Player Info                  |");
            System.out.println("+-------------------------------------------------+");
            System.out.printf("| Record: %-37s\n", player.getRecord());
            System.out.printf("| Health: %-37s\n", character.getHealth());
            System.out.printf("| Gold: %-37s\n", character.getGold());
            System.out.printf("| Power: %-37s\n", character.getPower());
            System.out.printf("| Armor: %-37s\n", character.getArmor().getName());
            System.out.printf("| Weapon: %-37s\n", character.getWeapon().getName());
            

        }

        // Admin admin = user.getAdmin();
        // if (admin != null) {
        //     System.out.println("| Admin Info:                                     |");
            
        // }
        // System.out.println("+-------------------------------------------------+");
    }

    public static void inventoryMenu(ArrayList<Inventory> inventories, Scanner scanner, Player player) {
        Character character = player.getCharacter();

        System.out.println("+-------------------------------------------------+");
        System.out.println("|                    Inventory                    |");
        System.out.println("+-------------------------------------------------+");

        for (Inventory inventory : inventories) {
            // Weapons
            System.out.println("| Weapons:                                        |");
            if (!inventory.getWeapons().isEmpty()) {
                for (Weapon weapon : inventory.getWeapons()) {
                    System.out.printf("|%-15s Attack Mod: %-3d Defense Mod: %-3d Hand Space: %-3d \n",
                            weapon.getName(), weapon.getModAttack(), weapon.getModDefense(), weapon.getSpaceHand());
                }
            } else {
                System.out.println("|   No weapons in inventory.                      |");
            }

            System.out.println("+-------------------------------------------------+");
            // Armors
            System.out.println("| Armors:                                         |");
            if (!inventory.getArmors().isEmpty()) {
                for (Armor armor : inventory.getArmors()) {
                    System.out.printf("| %-15s Attack Mod: %-3d Defense Mod: %-3d\n",
                            armor.getName(), armor.getModAttack(), armor.getModDefense());
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
    }
}
