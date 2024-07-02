package SystemFunction;

import Character.Character;
import Character.CharacterFactory;
import User.Player;
import User.User;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CombateTest {

    @Test
    void testInitialCombat() {
        User newUser = new User("testBot", null, null, "player");
        User secondUser = new User("testBot2", null, null, "player");
        Player player1 = new Player();
        Player player2 = new Player();

        newUser.setPlayer(player1);
        secondUser.setPlayer(player2);

        Character newCharacter = CharacterFactory.createCharacter("vampire", "newBot", 100, 100, 20, 0, 0, 0);
        Character secondCharacter = CharacterFactory.createCharacter("hunter", "secondBot", 100, 100, 20, 0, 0, 0);

        newUser.getPlayer().setCharacter(newCharacter);
        secondUser.getPlayer().setCharacter(secondCharacter);

        Character challengerCharacter = newUser.getPlayer().getCharacter();
        challengerCharacter.setType("vampire");
        Character challengedCharacter = secondUser.getPlayer().getCharacter();
        challengedCharacter.setType("hunter");

        Combate combat = new Combate(newUser, secondUser, 10);

        combat.initialCombat();

        assertTrue(challengerCharacter.getGold() < 100 || challengedCharacter.getGold() < 100);
    }

}
