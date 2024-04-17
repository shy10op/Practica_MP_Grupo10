package User;

import java.io.Serializable;
import java.util.ArrayList;

import Database.Initdata;
import SystemFunction.Combate;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nick;
    private String password;
    private String name;
    private Boolean accountStatus;
    private Combate combate;

    private String role;
    private Boolean logged;
    private Player player;
    private Admin admin;

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

    public static void deleteUser(String nick){
        ArrayList<User> users = Initdata.getUsers();
        User userToRemove = User.findUser(nick);
        userToRemove.setLogged(false);
        users.remove(userToRemove);
        Initdata.saveUsersToFile();
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

    public Boolean getLogged() {
        return logged;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    };

    public Admin getAdmin() {
        return admin;
    }

    public Combate getCombate() {
        return combate;
    }

    public void setCombate(Combate combate) {
        this.combate = combate;
    }

}
