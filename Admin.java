package User;

public class Admin extends User {

    public Admin(String nick, String password, String name) {
        super(nick, password, name, true);
    }

    @Override
    public void mainMenu() {
        System.out.println("   Main Menu");
        System.out.println("1. Edit Character");
        System.out.println("2. Add Items To The Character");
        System.out.println("3. Validate Challenges");
        System.out.println("4. Block Players");
        System.out.println("5. Unlock Players");
        System.out.println("6. Exit");
    }
}
