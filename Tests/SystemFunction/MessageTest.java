package SystemFunction;

import Database.Initdata;
import User.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeAll
    public static void setup() {
        Initdata.startInitData();
    }

    @Test
    public void sendCombatToAdminTest() {
        ArrayList<Combate> combates = Initdata.getCombates();

        User user1 = new User("testBot1", "12345", "testBot1", "player");
        User user2 = new User("testBot2", "12345", "testBot2", "player");
        int amount = 10;
        Combate combate = new Combate(user1, user2, amount);
        Message.sendCombatToAdmin(combate);
        assertTrue(combates.contains(combate));
    }

    @Test
    public void sendCombatToChallengedTest() {
        ArrayList<Combate> combates = Initdata.getCombates();

        User user1 = new User("testBot1", "12345", "testBot1", "player");
        User user2 = new User("testBot2", "12345", "testBot2", "player");
        int amount = 10;
        Combate combate = new Combate(user1, user2, amount);
        combates.add(combate);
        Message.sendCombatToChallenged(combate);
        assertNotNull(user2.getCombate());
    }

    @Test
    public void receiveCombatTest() {
        ArrayList<Combate> combates = Initdata.getCombates();

        User user1 = new User("testBot1", "12345", "testBot1", "player");
        User user2 = new User("testBot2", "12345", "testBot2", "player");
        int amount = 10;

        Combate combate = new Combate(user1, user2, amount);

        combates.add(combate);
        Message.sendCombatToChallenged(combate);
        Message.receiveCombat(user2);
        assertEquals(user2.getCombate(), combate);
    }
}
