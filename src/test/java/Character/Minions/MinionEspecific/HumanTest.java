package Character.Minions.MinionEspecific;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import Character.Minions.Minion;
import Character.Minions.MinionEspecific.Human.Loyalty;
import Character.Minions.MinionFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yshdo
 */
public class HumanTest {

    private Human human;

    @BeforeEach
    public void setUp() {
        Minion minion = MinionFactory.createMinion("human", "John", 100, Loyalty.HIGH, null, null, 0);
        human = ((Human) minion);
    }

    @Test
    public void testGetLoyalty() {
        assertEquals(Loyalty.HIGH, human.getLoyalty());
    }

    @Test
    public void testSetLoyalty() {
        human.setLoyalty(Loyalty.LOW);
        assertEquals(Loyalty.LOW, human.getLoyalty());
    }

    @Test
    public void testGetName() {
        assertEquals("John", human.getName());
    }

    @Test
    public void testSetName() {
        human.setName("Doe");
        assertEquals("Doe", human.getName());
    }

    @Test
    public void testGetHealth() {
        assertEquals(100, human.getHealth());
    }

    @Test
    public void testSetHealth() {
        human.setHealth(120);
        assertEquals(120, human.getHealth());
    }
}
