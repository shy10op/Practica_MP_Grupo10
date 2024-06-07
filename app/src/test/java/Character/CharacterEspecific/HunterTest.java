package Character.CharacterEspecific;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Character.AbilityStrategy.Ability;
import Character.Character;
import Character.CharacterFactory;
import org.junit.jupiter.api.Test;

class HunterTest {

  @Test // return expected value 3
  public void testGetWillpower() {
    Character hunter = CharacterFactory.createCharacter(
      "hunter",
      "Van Helsing",
      100,
      100,
      15,
      0,
      0,
      0
    );
    hunter.setType("hunter");
    assertEquals(3, ((Hunter) hunter).getWillpower());
  }

  @Test
  public void testSetWillpower() {
    Character hunter = CharacterFactory.createCharacter(
      "hunter",
      "Van Helsing",
      100,
      100,
      15,
      0,
      0,
      0
    );
    hunter.setType("hunter");
    ((Hunter) hunter).setWillpower(5);
    assertEquals(5, ((Hunter) hunter).getWillpower());
  }

  @Test
  void testSetTalent() {
    Character hunter = CharacterFactory.createCharacter(
      "hunter",
      "Van Helsing",
      100,
      100,
      15,
      0,
      0,
      0
    );
    hunter.setType("hunter");
    ((Hunter) hunter).setTalent(new Ability.HunterAbility());
    ((Hunter) hunter).useAbility();
    assertEquals(18, hunter.getPower());
  }

  @Test
  void testUseAbility() {
    Character hunter = CharacterFactory.createCharacter(
      "hunter",
      "hunter",
      100,
      100,
      10,
      0,
      0,
      0
    );
    hunter.setType("hunter");
    int power = hunter.getPower();
    ((Hunter) hunter).setTalent(new Ability.HunterAbility());
    ((Hunter) hunter).useAbility();

    assertTrue(((Hunter) hunter).getWillpower() == 3);
    assertTrue(power < hunter.getPower());
  }
}
