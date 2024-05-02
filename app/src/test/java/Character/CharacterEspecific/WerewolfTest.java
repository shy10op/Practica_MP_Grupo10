package Character.CharacterEspecific;

import Character.AbilityStrategy.Ability;
import Character.Character;
import Character.CharacterFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WerewolfTest {

    @Test
    void testGetRage() {
        Character werewolf = CharacterFactory.createCharacter("werewolf", "Wolf", 150, 120, 20, 0, 0, 3);
        werewolf.setType("werewolf");
        assertEquals(3, ((Werewolf) werewolf).getRage());
    }

    @Test
    void testSetRage() {
        Character werewolf = CharacterFactory.createCharacter("werewolf", "Wolf", 150, 120, 20, 0, 0, 3);
        werewolf.setType("werewolf");
        ((Werewolf) werewolf).setRage(5);
        assertEquals(5, ((Werewolf) werewolf).getRage());
    }

    @Test
    void testSetDon() {
        Character werewolf = CharacterFactory.createCharacter("werewolf", "Wolf", 150, 120, 20, 0, 0, 3);
        werewolf.setType("werewolf");
        ((Werewolf) werewolf).setDon(new Ability.WereWolfAbility());
        int auxPower = ((Werewolf)werewolf).getPower();
        ((Werewolf)werewolf).useAbility();
        boolean test = false;
        if (auxPower < werewolf.getPower()){
            test = true;
        }
        assertTrue(test);
    }
    @Test
    void testUseAbility(){
        Character werewolf = CharacterFactory.createCharacter("werewolf", "Wolf", 150, 120, 20, 0, 0, 3);
        werewolf.setType("werewolf");
        ((Werewolf) werewolf).setDon(new Ability.WereWolfAbility());
        int auxPower = ((Werewolf)werewolf).getPower();
        ((Werewolf)werewolf).useAbility();
        boolean test = false;
        if (auxPower < werewolf.getPower()){
            test = true;
        }
        assertTrue(test);
    }
}