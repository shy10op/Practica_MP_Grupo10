package User;

import Database.Initdata;
import SystemFunction.Menu;
import java.util.ArrayList;

public class Admin extends User {

  public Admin() {}

  public Admin(String nick, String password, String name) {
    super(nick, password, name, "admin");
  }

  public static void showPlayers() {
    ArrayList<User> playerList = getPlayers();
    Menu.printHeader("Players");
    for (User user : playerList) {
      Player player = user.getPlayer();
      System.out.println(player.getNick() + " - " + player.getRecord());
    }
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

  public static void banUser(String nick) {
    User user = User.findUser(nick);
    if (user != null) {
      if (user.getAccountStatus()) {
        user.setAccountStatus(false);
        System.out.println("Player banned successfully");
      }
    } else {
      System.out.println("User not found");
    }
  }

  public static void unBanUser(String nick) {
    User user = User.findUser(nick);
    if (user != null) {
      if (user.getAccountStatus() == false) {
        user.setAccountStatus(true);
        System.out.println("Player unBanned successfully");
      }
    } else {
      System.out.println("User not found");
    }
  }
}
