package SystemFunction;

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
        System.out.println("+------------------------------------------------+");
    }

    public static void AdminOpcion1() {
        System.out.println("+------------------Change Feature---------------+");
        System.out.println("| 1. Name                                       |");
        System.out.println("| 2. Power                                      |");
        System.out.println("| 3. Health                                     |");
        System.out.println("| 4. Exit                                       |");
        System.out.println("+------------------------------------------------+");
    }
}
