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
    void setWillpower() {
    }

    @Test
    void setTalento() {
    }

    @Test
    void useAbility() {
    }
}