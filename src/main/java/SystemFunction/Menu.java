package SystemFunction;

import Character.Character;
import Character.CharacterFactory;
import Character.Equipment.Armor;
import Character.Equipment.EquipmentFactory;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import Database.Initdata;
import User.Admin;
import User.Player;
import User.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    System.out.println("| 2. Edit Inventory                               |");
    System.out.println("| 3. Validate Challenges                          |");
    System.out.println("| 4. Block Players                                |");
    System.out.println("| 5. Unlock Players                               |");
    System.out.println("| 6. Exit                                         |");
    System.out.println("+-------------------------------------------------+");
    System.out.print("Choose an option: ");
  }

  // Method to safely get an integer input from the user
  public static int safeNextInt(Scanner scanner) {
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. Please enter a valid number.");
      scanner.next(); // Discard the incorrect input
    }
    return scanner.nextInt();
  }

  // Method to safely get a string that matches a list of valid options
  public static String safeNextType(Scanner scanner, List<String> validTypes) {
    String input = scanner.nextLine().trim();
    while (!validTypes.contains(input.toLowerCase())) {
      System.out.println(
        "Invalid type. Please enter one of the following: " + validTypes
      );
      input = scanner.nextLine().trim();
    }
    return input;
  }

  public static void changeUserCharacterMenu(
    User destinationUser,
    Scanner scanner
  ) {
    printHeader("User");

    System.out.printf("User Nick: %s To ", destinationUser.getNick());
    String newNick = scanner.nextLine();
    destinationUser.setNick(newNick);

    System.out.printf("User Name: %s To ", destinationUser.getName());
    String newUserName = scanner.nextLine();
    destinationUser.setName(newUserName);

    printHeader("Character");
    Character destinationCharacter = destinationUser.getPlayer().getCharacter();
    System.out.printf("Character Name: %s To ", destinationCharacter.getName());
    String newName = scanner.nextLine();
    destinationCharacter.setName(newName);

    System.out.printf(
      "Character Health: %d To ",
      destinationCharacter.getHealth()
    );
    int newHealth = safeNextInt(scanner);
    scanner.nextLine(); // consume the newline
    destinationCharacter.setHealth(newHealth);

    System.out.printf(
      "Character Power: %d To ",
      destinationCharacter.getPower()
    );
    int newPower = safeNextInt(scanner);
    scanner.nextLine(); // consume the newline
    destinationCharacter.setPower(newPower);

    System.out.printf("Character Gold: %d To ", destinationCharacter.getGold());
    int newGold = safeNextInt(scanner);
    scanner.nextLine(); // consume the newline
    destinationCharacter.setGold(newGold);

    System.out.printf(
      "Character Type: %s To (hunter, vampire, werewolf) ",
      destinationCharacter.getType()
    );
    List<String> validTypes = Arrays.asList("hunter", "vampire", "werewolf");
    String newType = safeNextType(scanner, validTypes);
    destinationCharacter.setType(newType);

    if (newType.equals("vampire") || newType.equals("werewolf")) {
      if (newType.equals("vampire")) {
        System.out.print("Set a new Age: ");
        int newAge = safeNextInt(scanner);
        System.out.print("Set a new Blood: ");
        int newBlood = safeNextInt(scanner);
        destinationCharacter =
          CharacterFactory.createCharacter(
            newType,
            newName,
            newGold,
            newHealth,
            newPower,
            newBlood,
            newAge,
            0
          );
      } else if (newType.equals("werewolf")) {
        System.out.print("Set a new Rage: ");
        int newRage = safeNextInt(scanner);
        destinationCharacter =
          CharacterFactory.createCharacter(
            newType,
            newName,
            newGold,
            newHealth,
            newPower,
            0,
            0,
            newRage
          );
      }
    } else {
      destinationCharacter =
        CharacterFactory.createCharacter(
          newType,
          newName,
          newGold,
          newHealth,
          newPower,
          0,
          0,
          0
        );
    }

    destinationUser.getPlayer().setCharacter(destinationCharacter);
    System.out.println("User character updated successfully.");
    Initdata.saveUsersToFile();
  }

  public static void combatListMenu() {
    printHeader("Combat List");
    ArrayList<Combate> combates = Initdata.getCombateList();
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
      System.out.printf(
        "| %s VS %s" + "  Gold: %d \n",
        challengerName,
        challengedName,
        combate.getAmount()
      );
    }
    System.out.println("+-------------------------------------------------+");
    System.out.print("\n");
  }

  public static Combate combatMenu(String nick) {
    printHeader("Combat found involving " + nick + ":");
    ArrayList<Combate> combates = Initdata.getCombateList();
    boolean found = false;
    for (Combate combate : combates) {
      if (
        combate.getChallenger().getNick().equals(nick) ||
        combate.getChallenged().getNick().equals(nick)
      ) {
        Character challenger = combate
          .getChallenger()
          .getPlayer()
          .getCharacter();
        Character challenged = combate
          .getChallenged()
          .getPlayer()
          .getCharacter();
        printHeader("Challenger: ");
        printCharacterInfo(challenger);
        printHeader("Challenged: ");
        printCharacterInfo(challenged);

        System.out.println(
          "+-------------------------------------------------+"
        );
        System.out.printf("| Stake: %d\n", combate.getAmount());
        System.out.println(
          "+-------------------------------------------------+"
        );
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
    System.out.printf(
      "| Name: %s, Type: %s, Health: %d, Power: %d, Gold: %d\n",
      character.getName(),
      character.getType(),
      character.getHealth(),
      character.getPower(),
      character.getGold()
    );
    if (character.getWeapon() != null) {
      System.out.println(
        "| Weapon: " +
        character.getWeapon().getName() +
        "  Attack Mod: " +
        character.getWeapon().getModAttack() +
        "  Defense Mod: " +
        character.getWeapon().getModDefense()
      );
    } else {
      System.out.println("| Weapon: None");
    }
    if (character.getArmor() != null) {
      System.out.println(
        "| Armor: " +
        character.getArmor().getName() +
        "  Attack Mod: " +
        character.getArmor().getModAttack() +
        "  Defense Mod: " +
        character.getArmor().getModDefense()
      );
    } else {
      System.out.println("| Armor: None");
    }
    if (character.getMinion() != null) {
      System.out.println(
        "| Minion: " +
        character.getMinion().getName() +
        "  Type: " +
        character.getMinion().getType() +
        "  Health: " +
        character.getMinion().getHealth()
      );
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
        System.out.printf(
          "| Minion: %-38s \n",
          character.getMinion().getType()
        );
      }
      if (character.getArmor() != null) {
        System.out.printf("| Armor: %-38s \n", character.getArmor().getName());
      }
      if (character.getWeapon() != null) {
        System.out.printf(
          "| Weapon: %-38s \n",
          character.getWeapon().getName()
        );
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
    List<User> filteredSortedUsers = users
      .stream()
      .filter(u -> u.getPlayer() != null && u.getPlayer().getCharacter() != null
      )
      .sorted((u1, u2) ->
        Integer.compare(
          u2.getPlayer().getCharacter().getGold(),
          u1.getPlayer().getCharacter().getGold()
        )
      )
      .collect(Collectors.toList());

    int rank = 1;
    for (User user : filteredSortedUsers) {
      if (rank > 20) break;
      if (user.getAccountStatus()) {
        int gold = user.getPlayer().getCharacter().getGold();
        System.out.printf(
          "| %d. %-20s Gold: %d \n",
          rank,
          user.getName(),
          gold
        );
        rank++;
      }
    }
    System.out.println("\n");
  }

  public static void inventoryMenu(
    Inventory inventory,
    Scanner scanner,
    User user
  ) {
    printHeader("Inventory");
    System.out.println("| Weapons:                                        ");
    if (!inventory.getWeapons().isEmpty()) {
      for (Weapon weapon : inventory.getWeapons()) {
        System.out.printf(
          "| %-47s \n",
          weapon.getName() +
          " Attack Mod: " +
          weapon.getModAttack() +
          " Defense Mod: " +
          weapon.getModDefense() +
          " Hand Space: " +
          weapon.getSpaceHand()
        );
      }
    } else {
      System.out.println("|   No weapons in inventory.                      ");
    }

    // Armors
    System.out.println("| Armors:                                         ");
    if (!inventory.getArmors().isEmpty()) {
      for (Armor armor : inventory.getArmors()) {
        System.out.printf(
          "| %-47s \n",
          armor.getName() +
          " Attack Mod: " +
          armor.getModAttack() +
          " Defense Mod: " +
          armor.getModDefense()
        );
      }
    } else {
      System.out.println("|   No armors in inventory.                       ");
    }

    if (user.getRole().equals("player")) {
      Character character = user.getPlayer().getCharacter();
      System.out.println("+-------------------------------------------------+");
      System.out.println("Enter the name of the weapon you'd like to choose:");
      String weaponName = scanner.nextLine();
      Weapon chosenWeapon = inventory
        .getWeapons()
        .stream()
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
      Armor chosenArmor = inventory
        .getArmors()
        .stream()
        .filter(armor -> armor.getName().equalsIgnoreCase(armorName))
        .findFirst()
        .orElse(null);

      if (chosenArmor != null) {
        character.setArmor(chosenArmor);
        System.out.println("You have chosen: " + chosenArmor.getName());
      } else {
        System.out.println("Armor not found.");
      }
    } else if (user.getRole().equals("admin")) {
      System.out.println("+-------------------------------------------------+");
      System.out.println("Edit Inventory (add/delete/exit)");
      String op = scanner.nextLine();
      switch (op) {
        case "add":
          System.out.println("Enter the type (armor/weapon) :");
          String type = scanner.nextLine();
          System.out.println("Enter the Name:");
          String name = scanner.nextLine();
          System.out.println("Enter the Mod Atack:");
          int modAttack = scanner.nextInt();
          System.out.println("Enter the Mod Defense:");
          int modDefense = scanner.nextInt();
          System.out.println("Enter the Space Hand:");
          int spaceHand = scanner.nextInt();

          if (type.equals("weapon")) {
            Weapon weapon = EquipmentFactory.createWeapon(
              name,
              modAttack,
              modDefense,
              spaceHand
            );
            inventory.addWeapon(weapon);
          } else if (type.equals("armor")) {
            Armor armor = EquipmentFactory.createArmor(
              name,
              modAttack,
              modDefense
            );
            inventory.addArmor(armor);
          }
          break;
        case "delete":
          System.out.println(
            "Enter the type (armor/weapon) you want to delete:"
          );
          String deleteType = scanner.nextLine();
          System.out.println("Enter the name of the item to delete:");
          String itemName = scanner.nextLine();
          if (deleteType.equalsIgnoreCase("weapon")) {
            Weapon weaponToDelete = inventory
              .getWeapons()
              .stream()
              .filter(w -> w.getName().equalsIgnoreCase(itemName))
              .findFirst()
              .orElse(null);
            if (weaponToDelete != null) {
              inventory.getWeapons().remove(weaponToDelete);
              System.out.println(itemName + " has been removed from weapons.");
            } else {
              System.out.println("Weapon not found.");
            }
          } else if (deleteType.equalsIgnoreCase("armor")) {
            Armor armorToDelete = inventory
              .getArmors()
              .stream()
              .filter(a -> a.getName().equalsIgnoreCase(itemName))
              .findFirst()
              .orElse(null);
            if (armorToDelete != null) {
              inventory.getArmors().remove(armorToDelete);
              System.out.println(itemName + " has been removed from armors.");
            } else {
              System.out.println("Armor not found.");
            }
          }
          break;
        case "exit":
          break;
        default:
          System.out.println("Invalid option");
          break;
      }
      Initdata.saveInventoriesToFile();
    }
    Initdata.saveUsersToFile();
    System.out.println("\n");
  }

  public static void showPlayer() {
    ArrayList<User> userList = Initdata.getUsers();
    for (User user : userList) {
      if (user.getRole().equals("player")) {
        System.out.println(user.getNick());
      }
    }
  }
}
