package Character.CharacterEspecific;

import Character.AbilityStrategy.Ability;
import Character.Character;
import Character.CharacterFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

class WerewolfTest {

    private static Character werewolf;

    @BeforeEach
    public void setUp() {
        werewolf = CharacterFactory.createCharacter("werewolf", "Wolf", 150, 120, 20, 0, 0, 3);
    }

    @Test
    void testUseAbility() {
        ((Werewolf) werewolf).setDon(new Ability.WereWolfAbility());
        int auxPower = ((Werewolf) werewolf).getPower();
        ((Werewolf) werewolf).useAbility();
        boolean test = false;
        if (auxPower < werewolf.getPower()) {
            test = true;
        }
        assertTrue(test);

    }
}
