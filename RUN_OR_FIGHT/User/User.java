package User;

import java.io.Serializable;

public class User implements Serializable {
    private String nick;
    private String password;
    private String name;
    private Boolean accountStatus;
    private Boolean role; // al hacer el login hay que hacer un comprobacion de role, para dirigir el
                          // usuario a diferentes menu
    private Boolean logged;

    // Constructor
    public User(String nick, String password, String name, Boolean role) {
        this.nick = nick;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User() {
    }

    // Getters and Setters

    public User findUser(String nick) {
        // Para hacer la busqueda de usuario por el nick
        User detinationUser = new User();
        return detinationUser;
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

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    // For future admin feature (ban user or unban user)
    public void setAccountStatus(Boolean status) {
        this.accountStatus = status;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }
}
