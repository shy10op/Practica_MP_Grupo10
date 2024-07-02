package Character.CharacterEspecific;

import static org.junit.jupiter.api.Assertions.*;

import Character.AbilityStrategy.Ability;
import Character.Character;
import Character.CharacterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HunterTest {

    private static Character hunter;

    @BeforeEach
    public void setUp() {
        hunter = CharacterFactory.createCharacter(
                "hunter",
                "Van Helsing",
                100,
                100,
                15,
                0,
                0,
                0
        );
    }

    @Test
    void testUseAbility() {
        ((Hunter) hunter).setTalent(new Ability.HunterAbility());
        int power = hunter.getPower();
        ((Hunter) hunter).useAbility();
        assertTrue(((Hunter) hunter).getWillpower() == 3);
        assertTrue(power < hunter.getPower());
    }
}
