package User;


import java.io.Serializable;
import java.util.ArrayList;

import Database.Initdata;

public class User implements Serializable {
    private String nick;
    private String password;
    private String name;
    private Boolean accountStatus;
    private String combatStatus;
    private Boolean role; // al hacer el login hay que hacer un comprobacion de role, para dirigir el
                          // usuario a diferentes menu
    private Boolean logged;

    Initdata initData = new Initdata();
    static ArrayList<User> users = Initdata.getUsers();

    // Constructor
    public User(String nick, String password, String name, Boolean role) {
        this.nick = nick;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User() {
    }  
    
    public static User findUser(String nick) { 
        for (User user : users) {
            if (user.getNick().equals(nick)) {
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


    // aqui cuando para hacer la comprobacion de que si tiene un combate pendiente, devuelve null en caso de que no
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

    public Initdata getInitData() {
        return initData;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

}
