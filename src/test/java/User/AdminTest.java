package User;

import Database.Initdata;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AdminTest {

    public static ArrayList<User> users = Initdata.getUsers();

    @BeforeAll
    public static void setup() {
        User userBotUnbanned = new User("testUnbanned", "12345678", "name", "player");
        User userBotBanned = new User("testBanned", "12345678", "name", "player");
        User userAdmin = new User("admin", "12345678", "name", "admin");
        userBotBanned.setAccountStatus(false);

        users.add(userAdmin);
        users.add(userBotUnbanned);
        users.add(userBotBanned);
        Initdata.saveUsersToFile();
    }

    @Test
    public void banUserTest() {
        try {
            String nick = "admin"; // un usuario que no esta baneado
            Admin.banUser(nick);//le baneamos

            User user = User.findUser(nick);//lo buscamos
            assertFalse(user.getAccountStatus());// comprobamos su estado de la cuenta
        } catch (Exception e) {
            fail("User not exist");
        }
    }

    @Test
    public void unBanUserTest() {
        try {
            String nick = "testBanned";
            User user = User.findUser(nick);

            Admin.unBanUser(nick);
            assertTrue(user.getAccountStatus());
        } catch (Exception e) {
            fail("User not exist");
        }

    }

}
