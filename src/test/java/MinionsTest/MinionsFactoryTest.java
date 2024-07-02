/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package MinionsTest;

import Character.Minions.Minion;
import Character.Minions.MinionEspecific.Demon;
import Character.Minions.MinionEspecific.Ghoul;
import Character.Minions.MinionEspecific.Human;
import Character.Minions.MinionEspecific.Human.Loyalty;
import Character.Minions.MinionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yshdo
 */
public class MinionsFactoryTest {

    @Test
    public void testCreateHuman() {
        Minion minion = MinionFactory.createMinion("human", "John", 100, Loyalty.HIGH, null, null, 0);//se crea un minion de tipo human
        assertTrue(minion instanceof Human);//comprobar que el minion creado es de tipo humano
    }

    @Test
    public void testCreateDemon() {
        Minion minion = MinionFactory.createMinion("demon", "Lucifer", 200, null, true, "Powerful", 0);//se crea un minion de tipo demon
        assertTrue(minion instanceof Demon);//comprobar que el minion creado es de tipo humano
    }

    @Test
    public void testCreateGhoul() {
        Minion minion = MinionFactory.createMinion("ghoul", "Ghoulie", 50, null, null, null, 10);
        assertTrue(minion instanceof Ghoul);
    }

    @Test
    public void testCreateUnknownType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {//capturar la exception
            MinionFactory.createMinion("unknown", "Unknown", 0, null, null, null, 0);
        });
        String expectedMessage = "Unknow Minion type : unknown";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));//comprueba la exception lanzada es igual al expectedMessage = "Unknow Minion type : unknown"
    }
}
