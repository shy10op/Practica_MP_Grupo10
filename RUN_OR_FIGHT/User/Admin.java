package User;

import java.util.ArrayList;

import Database.Initdata;

public class Admin extends User {

    private AdminTools adminTools;

    public Admin(String nick, String password, String name) {
        super(nick, password, name, "admin");
        this.adminTools = new AdminTools(getPlayers());
    }

    public static ArrayList<User> getPlayers() {
        ArrayList<User> users = Initdata.getUsers();
        ArrayList<User> playerList = new ArrayList<>();
        for (User user : users) {
            if (user.getPlayer() != null) {
                playerList.add(user);
            }
        }
        return playerList;
    }

    public void showPlayers() {
        ArrayList<User> playerList = Initdata.getUsers();
        for (User user : playerList) {
            if (user instanceof Player) {
                Player player = (Player) user;
                System.out.println(player.getNick() + " - " + player.getName());
            }
        }
    }

    public User findPlayerByNick(String nick) {
        return adminTools.findPlayerByNick(nick);
    }

    public void modifyCharacterAttributes(String nick, String attributeName, Object newValue) {
        adminTools.modifyCharacterAttributes(nick, attributeName, newValue);
    }

    public static void banUser(String nick) {
        User user = User.findUser(nick);
        if (user.getAccountStatus()) {
            user.setAccountStatus(false);
        } else if (user.getAccountStatus().equals(false)) {
            user.setAccountStatus(true);
        }
    }
}
