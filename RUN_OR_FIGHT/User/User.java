package User;

import java.io.Serializable;

public class User implements Serializable {
    private String nick;
    private String password;
    private String name;
    private Boolean accountStatus;
    private Boolean logged;

    public User(String nick, String password, String name) {
        this.nick = nick;
        this.password = password;
        this.name = name;
    }

    public Boolean isLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    // getters and setters
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

    public void setAccountStatus(Boolean status) {
        this.accountStatus = status;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

}
