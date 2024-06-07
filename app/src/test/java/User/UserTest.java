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
    Field field = Initdata.class.getDeclaredField("FILENAME");
    field.setAccessible(true);
    String filename = (String) field.get(null);
  }

  @AfterEach
  public void tearDown() throws Exception {
    Initdata.saveUsersToFile();
  }

  @Test
  public void testFindUserExists() {
    User testBot = new User("testBot", "12345678910111213", "BotName", "player");

    User result = User.findUser(testBot.getNick());
    assertNotNull(result);
    assertEquals(nickExistente.toLowerCase(), result.getNick().toLowerCase());
  }

  @Test
  public void testDeleteUser() {
    User testBot = new User("testBot", "12345678910111213", "BotName", "elfo");
    
    assertNotNull(testBot);
    ArrayList<User> users = Initdata.getUsers();
    users.add(testBot);
    Initdata.saveUsersToFile();

    User.deleteUser(testBot.getNick());

    User testBotDeleted = User.findUser(testBot.getNick());

    assertNull(testBotDeleted);
  }
}
