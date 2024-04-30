package SystemFunction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Database.Initdata;
import User.User;

public class MessageTest {

    @BeforeAll
    public static void setup() {
        Initdata.startInitData();
    }

    @Test
    public void sendCombatToAdminTest() {
        ArrayList<Combate> combates = Initdata.getCombates();

        User user1 = User.findUser("Bot1");
        User user2 = User.findUser("Bot2");
        int amount = 10;
        Combate combate = new Combate(user1, user2, amount);
        Message.sendCombatToAdmin(combate);
        assertTrue(combates.contains(combate));
    }

    @Test
    public void sendCombatToChallengedTest() {
        ArrayList<Combate> combates = Initdata.getCombates();

        User user1 = User.findUser("Bot1");
        User user2 = User.findUser("Bot2");
        int amount = 10;
        Combate combate = new Combate(user1, user2, amount);
        combates.add(combate);
        Message.sendCombatToChallenged(combate);
        assertNotNull(user2.getCombate());
    }

    @Test
    public void receiveCombatTest() {
        ArrayList<Combate> combates = Initdata.getCombates();
        User user1 = User.findUser("Bot1");
        User user2 = User.findUser("Bot2");
        int amount = 10;
        Combate combate = new Combate(user1, user2, amount);
        combates.add(combate);
        Message.sendCombatToChallenged(combate);
        Message.receiveCombat(user2);
        assertEquals(user2.getCombate(), combate);
    }
}
