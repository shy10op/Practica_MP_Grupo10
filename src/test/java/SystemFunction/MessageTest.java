package SystemFunction;

import Database.Initdata;
import User.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MessageTest {

    private static ArrayList<Combate> combates;
    private static Combate combate;
    private static User user1;
    private static User user2;

    @BeforeEach
    public void setup() {
        Initdata.startInitData();
        combates = Initdata.getCombateList();
        user1 = new User("testBot1", "12345", "testBot1", "player");
        user2 = new User("testBot1", "12345", "testBot2", "player");
        int amount = 10;
        combate = new Combate(user1, user2, amount);
    }

    @Test
    public void sendCombatToAdminTest() {
        Message.sendCombatToAdmin(combate);//guardar a la base de datos combateslist
        assertTrue(combates.contains(combate));
    }

    @Test
    public void sendCombatToChallengedTest() {
        Message.sendCombatToChallenged(combate);//enviar el combate al jugador 2
        assertNotNull(user2.getCombate());//comprobar que el jugador tiene ese combate
    }

    @Test
    public void receiveCombatTest() {//dato de entradad un usuario sin combate getcombat => null
        //un usuario con combate.
        Message.sendCombatToChallenged(combate);// enviar el combate al jugador 2
        Message.receiveCombat(user2);// jugador 2 recibe el combate 
        assertNotNull(user2.getCombate());// comprobar si es el mismo combate
        
    }
}
