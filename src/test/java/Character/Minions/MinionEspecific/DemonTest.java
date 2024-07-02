package Character.Minions.MinionEspecific;

import Character.Minions.Minion;
import Character.Minions.MinionFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemonTest {

    private Demon demon;

    @BeforeEach
    public void setUp() {
        Minion minion = MinionFactory.createMinion("demon", "Lucifer", 200, null, true, "Powerful", 0);

        demon = (Demon) minion;

    }

    @Test
    public void testGetPactDescription() {
        assertEquals("Powerful", demon.getPactDescription());
    }

    @Test
    public void testSetPactDescription() {
        demon.setPactDescription("Very Powerful");
        assertEquals("Very Powerful", demon.getPactDescription());
    }

    @Test
    public void testIsPact() {
        assertTrue(demon.isPact());
    }

    @Test
    public void testSetPact() {
        demon.setPact(false);
        assertFalse(demon.isPact());
    }

    @Test
    public void testGetName() {
        assertEquals("Lucifer", demon.getName());
    }

    @Test
    public void testSetName() {
        demon.setName("Satan");
        assertEquals("Satan", demon.getName());
    }

    @Test
    public void testGetHealth() {
        assertEquals(200, demon.getHealth());
    }

    @Test
    public void testSetHealth() {
        demon.setHealth(250);
        assertEquals(250, demon.getHealth());
    }

}
