package Character.Equipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WeaponTest {

    private Weapon weapon;

    @BeforeEach
    public void setUp() {
        weapon = new Weapon("Sword", 10, 2, 1);
    }

    @Test
    public void testDefaultConstructor() {
        Weapon defaultWeapon = new Weapon();
        assertNull(defaultWeapon.getName());
        assertEquals(0, defaultWeapon.getModAttack());
        assertEquals(0, defaultWeapon.getModDefense());
        assertEquals(0, defaultWeapon.getSpaceHand());
    }

    @Test
    public void testConstructorWithNameAttackAndSpaceHand() {
        Weapon attackWeapon = new Weapon("Dagger", 5, 1);
        assertEquals("Dagger", attackWeapon.getName());
        assertEquals(5, attackWeapon.getModAttack());
        assertEquals(0, attackWeapon.getModDefense());
        assertEquals(1, attackWeapon.getSpaceHand());
    }

    @Test
    public void testConstructorWithNameAttackDefenseAndSpaceHand() {
        assertEquals("Sword", weapon.getName());
        assertEquals(10, weapon.getModAttack());
        assertEquals(2, weapon.getModDefense());
        assertEquals(1, weapon.getSpaceHand());
    }

    @Test
    public void testGetName() {
        assertEquals("Sword", weapon.getName());
    }

    @Test
    public void testSetName() {
        weapon.setName("Axe");
        assertEquals("Axe", weapon.getName());
    }

    @Test
    public void testGetModAttack() {
        assertEquals(10, weapon.getModAttack());
    }

    @Test
    public void testSetModAttack() {
        weapon.setModAttack(15);
        assertEquals(15, weapon.getModAttack());
    }

    @Test
    public void testGetModDefense() {
        assertEquals(2, weapon.getModDefense());
    }

    @Test
    public void testSetModDefense() {
        weapon.setModDefense(3);
        assertEquals(3, weapon.getModDefense());
    }

    @Test
    public void testGetSpaceHand() {
        assertEquals(1, weapon.getSpaceHand());
    }

    @Test
    public void testSetSpaceHand() {
        weapon.setSpaceHand(2);
        assertEquals(2, weapon.getSpaceHand());
    }
}