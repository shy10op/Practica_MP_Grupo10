/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Character.Minions.MinionEspecific;

import Character.Minions.Minion;
import Character.Minions.MinionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yshdo
 */
public class GhoulTest {

    private Ghoul ghoul;

    @BeforeEach
    public void setUp() {
        Minion minion = MinionFactory.createMinion("ghoul", "Ghoulie", 50, null, null, null, 3);
        ghoul = ((Ghoul) minion);

    }
    
    @Test
    public void testGetDependenceOnMaster() {
        assertEquals(3, ghoul.getDependenceOnMaster());
    }

    @Test
    public void testSetDependenceOnMaster() {

        // Set dependenceOnMaster to a different value
        ghoul.setDependenceOnMaster(4);
        assertEquals(4, ghoul.getDependenceOnMaster());

        // Set dependenceOnMaster to another value
        ghoul.setDependenceOnMaster(2);
        assertEquals(2, ghoul.getDependenceOnMaster());
    }

}
