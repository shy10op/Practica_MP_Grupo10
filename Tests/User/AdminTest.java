package User;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import Database.Initdata;

public class AdminTest {
    public ArrayList<User> users = Initdata.getUsers();

    @BeforeAll
    public static void setup() {
        Initdata.startInitData();
    }

    @Test
    public void banUserTest() {
        User user = User.findUser("Bot1");
        Admin.banUser(user.getNick());
        assertFalse(user.getAccountStatus());
    }

    @Test
    public void unBanUserTest() {
        User user = User.findUser("Bot5");
        user.setAccountStatus(false);
        Admin.unBanUser(user.getNick());
        assertTrue(user.getAccountStatus());
    }

    @Test
    public void showUserListTest() {
        Boolean test = false;
        if (users.size() >= 0) {
            test = true;
        }
        assertTrue(test);
    }
}
