package User;

public class Admin extends User {

    public Admin(String nick, String password, String name) {
        super(nick, password, name, true);
    }
}
