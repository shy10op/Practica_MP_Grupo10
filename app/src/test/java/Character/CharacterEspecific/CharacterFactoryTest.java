package Character.CharacterEspecific;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Character.Character;
import Character.CharacterFactory;
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
    assertNotNull(hunter);
    assertTrue(hunter instanceof Hunter);
    assertEquals("Robin", hunter.getName());
    assertEquals(100, hunter.getGold());
    assertEquals(150, hunter.getHealth());
    assertEquals(20, hunter.getPower());
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
    assertNotNull(vampire);
    assertTrue(vampire instanceof Vampire);
    assertEquals("Dracula", vampire.getName());
    assertEquals(200, vampire.getGold());
    assertEquals(300, vampire.getHealth());
    assertEquals(40, vampire.getPower());
    assertEquals(50, ((Vampire) vampire).getBlood());
    assertEquals(400, ((Vampire) vampire).getAge());
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
    assertNotNull(werewolf);
    assertTrue(werewolf instanceof Werewolf);
    assertEquals("Lycan", werewolf.getName());
    assertEquals(150, werewolf.getGold());
    assertEquals(250, werewolf.getHealth());
    assertEquals(35, werewolf.getPower());
    assertEquals(60, ((Werewolf) werewolf).getRage());
  }

  @Test
  public void testCreateCharacterInvalidType() {
    Exception exception = assertThrows(
      IllegalArgumentException.class,
      () -> {
        CharacterFactory.createCharacter("elf", "Elrond", 50, 100, 15, 0, 0, 0);
      }
    );
    assertEquals("Unknown Character type : elf", exception.getMessage());
  }
}
