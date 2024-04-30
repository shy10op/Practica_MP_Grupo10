package Character.Equipment;

import Database.Initdata;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentFactoryTest {

    @Test //modo ataque y defensa
    public void testCreateWeapon() {
        Weapon weapon = EquipmentFactory.createWeapon("nameAttackDefense", 1, 1, 1);
        Inventory inventory = Initdata.getInventories();
        inventory.addWeapon(weapon);
        Initdata.saveInventoriesToFile();
        assertNotNull(Inventory.findWeapon("nameAttackDefense"));
    }

    @Test //modo ataque
    public void testCreateWeapon2() {
        Weapon weapon = EquipmentFactory.createWeapon("nameAttack", 1, 1);
        Inventory inventory = Initdata.getInventories();
        inventory.addWeapon(weapon);
        Initdata.saveInventoriesToFile();
        assertNotNull(Inventory.findWeapon("nameAttack"));
    }

    @Test //modo ataque y defensa
    public void testCreateArmor() {
        Armor armor = EquipmentFactory.createArmor("nameAttackDefense", 1, 1);
        Inventory inventory = Initdata.getInventories();
        inventory.addArmor(armor);
        Initdata.saveInventoriesToFile();
        assertNotNull(Inventory.findArmor("nameAttackDefense"));
    }

    @Test
    public void testCreateArmor2() {
        Armor armor = EquipmentFactory.createArmor("nameDefense", 1);
        Inventory inventory = Initdata.getInventories();
        inventory.addArmor(armor);
        Initdata.saveInventoriesToFile();
        assertNotNull(Inventory.findArmor("nameDefense"));
    }
}