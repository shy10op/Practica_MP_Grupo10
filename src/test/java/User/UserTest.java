package User;

import static org.junit.jupiter.api.Assertions.*;

import Database.Initdata;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  @BeforeEach
  public void setup() throws Exception {
    Initdata.startInitData();
  }

  @AfterEach
  public void tearDown() throws Exception {
    Initdata.saveUsersToFile();
  }

  @Test
  public void testFindUserExists() {
    User testBot = new User("testBot", "123456789101112", "BotName", "player");
    
    assertNotNull(testBot);
    ArrayList<User> users = Initdata.getUsers();
    users.add(testBot);
    Initdata.saveUsersToFile();

    User result = User.findUser(testBot.getNick());
    assertNotNull(result);
    assertEquals(testBot.getNick().toLowerCase(), result.getNick().toLowerCase());
  } 

  @Test
  public void testDeleteUser() {
    String nick = "";
    ArrayList<User> users = Initdata.getUsers();
    int size = users.size();
    User.deleteUser(nick);
    int newSize = users.size();
    assertTrue(size > newSize);
  }
    
}
