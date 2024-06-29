package User;

import Database.Initdata;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AdminTest {

    public ArrayList<User> users = Initdata.getUsers();

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
        if (users.size() >= 1) {
            test = true;
        }
        assertTrue(test);
    }
}
