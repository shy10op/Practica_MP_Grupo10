package User;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String nick, String password, String name) {
        super(nick, password, name, "admin");
    }
}
