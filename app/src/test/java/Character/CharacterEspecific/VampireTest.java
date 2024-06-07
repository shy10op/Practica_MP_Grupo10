package Character.CharacterEspecific;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Character.AbilityStrategy.Ability;
import Character.Character;
import Character.CharacterFactory;
import org.junit.jupiter.api.Test;

class VampireTest {

  @Test
  void testGetBlood() {
    Character vampire = CharacterFactory.createCharacter(
      "vampire",
      "Dracula",
      200,
      150,
      10,
      5,
      400,
      0
    );
    vampire.setType("vampire");
    assertEquals(5, ((Vampire) vampire).getBlood());
  }

  @Test
  void testGetAge() {
    Character vampire = CharacterFactory.createCharacter(
      "vampire",
      "Dracula",
      200,
      150,
      10,
      5,
      400,
      0
    );
    vampire.setType("vampire");
    assertEquals(400, ((Vampire) vampire).getAge());
  }

  @Test
  void testSetDiscipline() {
    Character vampire = CharacterFactory.createCharacter(
      "vampire",
      "Dracula",
      200,
      150,
      10,
      5,
      400,
      0
    );
    vampire.setType("vampire");
    ((Vampire) vampire).setDiscipline(new Ability.VampireAbility());
    int auxPower = vampire.getPower();
    ((Vampire) vampire).useAbility();
    boolean test = false;
    if (vampire.getPower() > auxPower) {
      test = true;
    }
    assertTrue(test);
  }

  @Test
  void testUseAbility() {
    Character vampire = CharacterFactory.createCharacter(
      "vampire",
      "Dracula",
      200,
      150,
      10,
      1,
      400,
      0
    );
    vampire.setType("vampire");
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
