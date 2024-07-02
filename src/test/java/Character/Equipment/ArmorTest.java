package Character.Equipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArmorTest {

    private Armor armor;

    @BeforeEach
    public void setUp() {
        armor = new Armor("Steel Armor", 5, 10);
    }

    @Test
    public void testDefaultConstructor() {
        Armor defaultArmor = new Armor();
        assertNull(defaultArmor.getName());
        assertEquals(0, defaultArmor.getModAttack());
        assertEquals(0, defaultArmor.getModDefense());
    }

    @Test
    public void testConstructorWithNameAndDefense() {
        Armor defenseArmor = new Armor("Iron Armor", 7);
        assertEquals("Iron Armor", defenseArmor.getName());
        assertEquals(0, defenseArmor.getModAttack());
        assertEquals(7, defenseArmor.getModDefense());
    }

    @Test
    public void testConstructorWithNameAttackAndDefense() {
        assertEquals("Steel Armor", armor.getName());
        assertEquals(5, armor.getModAttack());
        assertEquals(10, armor.getModDefense());
    }

    @Test
    public void testGetName() {
        assertEquals("Steel Armor", armor.getName());
    }

    @Test
    public void testSetName() {
        armor.setName("Golden Armor");
        assertEquals("Golden Armor", armor.getName());
    }

    @Test
    public void testGetModAttack() {
        assertEquals(5, armor.getModAttack());
    }

    @Test
    public void testSetModAttack() {
        armor.setModAttack(8);
        assertEquals(8, armor.getModAttack());
    }

    @Test
    public void testGetModDefense() {
        assertEquals(10, armor.getModDefense());
    }

    @Test
    public void testSetModDefense() {
        armor.setModDefense(15);
        assertEquals(15, armor.getModDefense());
    }
}