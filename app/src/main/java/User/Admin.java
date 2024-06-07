package User;

import Database.Initdata;
import SystemFunction.Menu;
import java.util.ArrayList;

public class Admin extends User {

  public Admin() {}

  public Admin(String nick, String password, String name) {
    super(nick, password, name, "admin");
  }

  public static void banUser(String nick) {
    User user = User.findUser(nick);
    if (user.getAccountStatus() && user != null) {
      user.setAccountStatus(false);
      System.out.println("Player banned successfully");
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
