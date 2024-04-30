package User;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import Database.Initdata;

public class AuthTest{
    public ArrayList<User> users = Initdata.getUsers();

    @BeforeAll
    public static void setup() {
        Initdata.startInitData();
    }

    @Test
    public void loginTest() {
        String nick = "Bot1";
        String password = "12345";
        User user = Auth.loginAuth(nick, password);
        assertNotNull(user);
    }

    @Test
    public void signInTest() {

    }

}
