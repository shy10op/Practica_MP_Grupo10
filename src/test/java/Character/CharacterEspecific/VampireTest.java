package Character.CharacterEspecific;

import static org.junit.jupiter.api.Assertions.assertTrue;

import Character.AbilityStrategy.Ability;
import Character.Character;
import Character.CharacterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VampireTest {

    private static Character vampire;

    @BeforeEach
    public void setUp() {
        vampire = CharacterFactory.createCharacter(
                "vampire",
                "Dracula",
                200,
                150,
                10,
                5,
                400,
                0
        );
    }

    @Test
    void testUseAbility() {
        ((Vampire) vampire).setDiscipline(new Ability.VampireAbility());
        int auxPower = vampire.getPower();
        ((Vampire) vampire).useAbility();
        boolean test = false;

        if (vampire.getPower() > auxPower) {
            test = true;
        }

        assertTrue(test);

    }
}
