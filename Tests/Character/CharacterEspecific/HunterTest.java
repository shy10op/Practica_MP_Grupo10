package Character.CharacterEspecific;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    //
    @Test // return expected value 3
    public void testGetWillpower() {
        Hunter hunter = new Hunter("Van Helsing", 100, 50, 10);
        assertEquals(3, hunter.getWillpower());
    }

    @Test
    public void testSetWillpower() {
        Hunter hunter = new Hunter("Van Helsing", 100, 50, 10);
        hunter.setWillpower(3);
        assertEquals(3, hunter.getWillpower());
    }

    @Test
    void testSetTalent() {
        Hunter hunter = new Hunter ("Van Helsing", 100, 50, 13);
        assertEquals(13, hunter.getPower());
    }

    @Test
    void testUseAbility() {
        Hunter hunter = new Hunter ("Van Helsing", 100, 50, 16);
        assertEquals(16, hunter.getPower());
    }
}