package User;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String nick;
    private String password;
    private String name;

    public User(String nick, String password, String name) {
        this.nick = nick;
        this.password = password;
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
