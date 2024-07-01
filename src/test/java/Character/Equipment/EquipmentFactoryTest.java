package Character.Equipment;

import Database.Initdata;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;

class EquipmentFactoryTest {
    private static Inventory inventory;
    
    @BeforeAll
    public static void setUp(){
        inventory = Initdata.getInventories();
    }

    @Test //modo ataque y defensa
    public void testCreateWeapon() {
        Weapon weapon = EquipmentFactory.createWeapon("nameAttackDefense", 1, 1, 1);
        inventory.addWeapon(weapon);
        assertNotNull(Inventory.findWeapon("nameAttackDefense"));
    }

    @Test //modo ataque y defensa
    public void testCreateArmor() {
        Armor armor = EquipmentFactory.createArmor("nameAttackDefense", 1, 1);
        inventory.addArmor(armor);
        assertNotNull(Inventory.findArmor("nameAttackDefense"));
    }
}
