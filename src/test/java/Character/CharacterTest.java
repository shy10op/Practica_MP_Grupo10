package Character;

import Character.AbilityStrategy.Ability;
import Character.Equipment.Armor;
import Character.Equipment.Weapon;
import Character.Minions.Minion;
import Character.Minions.MinionEspecific.Human;
import Character.Minions.MinionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CharacterTest {

    private Character character;
    private Weapon weapon;
    private Armor armor;
    private Minion minion;

    @BeforeEach
    public void setUp() {
        character = new Character("Hero", 100, 1000, 50);
        weapon = new Weapon("Sword", 10, 1);
        armor = new Armor("Shield", 5);
        minion = MinionFactory.createMinion("human", "John", 100, Human.Loyalty.HIGH, null, null, 0);
    }

    @Test
    public void testDefaultConstructor() {
        Character defaultCharacter = new Character();
        assertNull(defaultCharacter.getName());
        assertEquals(0, defaultCharacter.getGold());
        assertEquals(0, defaultCharacter.getHealth());
        assertEquals(0, defaultCharacter.getPower());
    }

    @Test
    public void testConstructorWithParameters() {
        assertEquals("Hero", character.getName());
        assertEquals(100, character.getGold());
        assertEquals(1000, character.getHealth());
        assertEquals(50, character.getPower());
    }

    @Test
    public void testGetName() {
        assertEquals("Hero", character.getName());
    }

    @Test
    public void testSetName() {
        character.setName("Warrior");
        assertEquals("Warrior", character.getName());
    }

    @Test
    public void testGetGold() {
        assertEquals(100, character.getGold());
    }

    @Test
    public void testSetGold() {
        character.setGold(150);
        assertEquals(150, character.getGold());
    }

    @Test
    public void testGetHealth() {
        assertEquals(1000, character.getHealth());
    }

    @Test
    public void testSetHealth() {
        character.setHealth(1200);
        assertEquals(1200, character.getHealth());
    }

    @Test
    public void testGetPower() {
        assertEquals(50, character.getPower());
    }

    @Test
    public void testSetPower() {
        character.setPower(70);
        assertEquals(70, character.getPower());
    }

    @Test
    public void testGetWeapon() {
        character.setWeapon(weapon);
        assertEquals(weapon, character.getWeapon());
    }

    @Test
    public void testSetWeapon() {
        character.setWeapon(weapon);
        assertEquals(weapon, character.getWeapon());
    }

    @Test
    public void testGetArmor() {
        character.setArmor(armor);
        assertEquals(armor, character.getArmor());
    }

    @Test
    public void testSetArmor() {
        character.setArmor(armor);
        assertEquals(armor, character.getArmor());
    }

    @Test
    public void testGetType() {
        assertNull(character.getType());
    }

    @Test
    public void testSetType() {
        character.setType("Warrior");
        assertEquals("Warrior", character.getType());
    }

    @Test
    public void testGetMinion() {
        character.setMinion(minion);
        assertEquals(minion, character.getMinion());
    }

    @Test
    public void testSetMinion() {
        character.setMinion(minion);
        assertEquals(minion, character.getMinion());
    }
}