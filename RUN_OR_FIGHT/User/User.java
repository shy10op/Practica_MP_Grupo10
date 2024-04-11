package User;

import java.io.Serializable;
import java.util.ArrayList;
import Character.Character;

import Database.Initdata;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nick;
    private String password;
    private String name;
    private Boolean accountStatus;
    private String combatStatus;
    private String role;
    private Boolean logged;
    private Character character;

    // Constructor
    public User(String nick, String password, String name, String role) {
        this.nick = nick;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User() {
    }

    public static User findUser(String nick) {
        ArrayList<User> users = Initdata.getUsers();
        for (User user : users) {
            if ((user.getNick().equalsIgnoreCase(nick))) {
                return user;
            }
        }
        return null;
    }

    // Getters and Setters

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // For future admin feature (ban user or unban user)
    public void setAccountStatus(Boolean status) {
        this.accountStatus = status;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    // aqui cuando para hacer la comprobacion de que si tiene un combate pendiente,
    // devuelve null en caso de que no
    // en caso de que si, devolveria un nick de user
    public String getCombatStatus() {
        return combatStatus;
    }

    public void setCombatStatus(String combatStatus) {
        this.combatStatus = combatStatus;
    }

    public Boolean getLogged() {
        return logged;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

}
