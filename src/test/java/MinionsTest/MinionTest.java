package MinionsTest;

import Character.Minions.Minion;
import Character.Minions.MinionEspecific.Human;
import Character.Minions.MinionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinionTest {
    
    private Minion minion;
    
    @BeforeEach
    public void setUp() {
        minion = MinionFactory.createMinion("human", "John", 100, Human.Loyalty.HIGH, null, null, 0);
        minion.setType("human");
    }
    
    @Test
    public void testConstructorWithParameters() {
        assertEquals("human", minion.getType());
        assertEquals("John", minion.getName());
        assertEquals(100, minion.getHealth());
        assertEquals(Human.Loyalty.HIGH, ((Human) minion).getLoyalty());
    }
    
    @Test
    public void testGetName() {
        assertEquals("John", minion.getName());
    }
    
    @Test
    public void testSetName() {
        minion.setName("Jane");
        assertEquals("Jane", minion.getName());
    }
    
    @Test
    public void testGetHealth() {
        assertEquals(100, minion.getHealth());
    }
    
    @Test
    public void testSetHealth() {
        minion.setHealth(120);
        assertEquals(120, minion.getHealth());
    }
    
    @Test
    public void testGetType() {
        assertEquals("human", minion.getType());
    }
    
    @Test
    public void testSetType() {
        minion.setType("human");
        assertEquals("human", minion.getType());
    }
    
    @Test
    public void testGetLoyalty() {
        assertEquals(Human.Loyalty.HIGH, ((Human) minion).getLoyalty());
    }
    
    @Test
    public void testSetLoyalty() {
        ((Human) minion).setLoyalty(Human.Loyalty.LOW);
        assertEquals(Human.Loyalty.LOW, ((Human) minion).getLoyalty());
    }
}
