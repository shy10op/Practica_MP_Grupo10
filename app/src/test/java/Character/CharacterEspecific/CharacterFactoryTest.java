package Character.CharacterEspecific;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Character.Character;
import Character.CharacterFactory;
import User.Player;
import org.junit.jupiter.api.Test;

public class CharacterFactoryTest {

  @Test
  public void testCreateHunter() {
    Character hunter = CharacterFactory.createCharacter(
      "hunter",
      "Robin",
      100,
      150,
      20,
      0,
      0,
      0
    );
    Player player = new Player();
    player.setCharacter(hunter);

    assertNotNull(player);
  }

  @Test
  public void testCreateVampire() {
    Character vampire = CharacterFactory.createCharacter(
      "vampire",
      "Dracula",
      200,
      300,
      40,
      50,
      400,
      0
    );
    Player player = new Player();
    player.setCharacter(vampire);

    assertNotNull(player);
  }

  @Test
  public void testCreateWerewolf() {
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
    Player player = new Player();
    player.setCharacter(werewolf);

    assertNotNull(player);
  }
}
