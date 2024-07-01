package Character.CharacterEspecific;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import Character.Character;
import Character.CharacterFactory;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class CharacterFactoryTest {

    @Test
    public void testCreateHunter() {
        try {
            Character hunter = CharacterFactory.createCharacter(
                    "hunter",
                    "Robin",
                    100,//oro
                    150,//vida
                    20,//poder
                    0,
                    0,
                    0
            );
            assertNotNull(hunter);
        } catch (IllegalArgumentException e) {
            fail("Cant create Character: " + e);
        }
    }

    @Test
    public void testCreateVampire() {
        try {
            Character vampire = CharacterFactory.createCharacter(
                    "vampire",
                    "Dracula",
                    200,//oro
                    300,//vida
                    40,//poder
                    50,
                    400,
                    0
            );
            assertNotNull(vampire);
        } catch (IllegalArgumentException e) {
            fail("Cant create Character: " + e);
        }
    }

    @Test
    public void testCreateWerewolf() {
        try {
            Character werewolf = CharacterFactory.createCharacter(
                    "werewolf",
                    "Lycan",
                    150,
                    250,
                    35,
                    0,
                    0,
                    60
            );
            assertNotNull(werewolf);
        } catch (IllegalArgumentException e) {
            fail("Cant create Character: " + e);
        }
    }
}
