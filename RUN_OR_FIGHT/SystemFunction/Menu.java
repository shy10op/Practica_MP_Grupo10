package SystemFunction;

public class Menu {

    public static void playerMenu() {
        System.out.println("   Main menu");
        System.out.println("1. Register Character");
        System.out.println("2. Unenroll Character");
        System.out.println("3. Modify Active Items");
        System.out.println("4. Challenge another user");
        System.out.println("5. Message");
        System.out.println("6. Check World Ranking");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");

    }

    public static void adminMenu() {
        System.out.println("   Main Menu");
        System.out.println("1. Edit Character");
        System.out.println("2. Add Items To The Character");
        System.out.println("3. Validate Challenges");
        System.out.println("4. Block Players");
        System.out.println("5. Unlock Players");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    public static void authMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }
}
