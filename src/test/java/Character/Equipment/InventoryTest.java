/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Character.Equipment;

import Database.Initdata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yshdo
 */
public class InventoryTest {

    private Inventory inventory;

    public InventoryTest() {
    }

    @BeforeEach
    public void setUp() {
        Initdata.startInitData();//cargar los datos
        inventory = Initdata.getInventories();
    }

    @Test
    public void addWeaponTest() {

        String name = "nameAttackDefense";
        Weapon weapon = EquipmentFactory.createWeapon(name, 1, 1, 1);
        int weaponListSize = Inventory.getWeapons().size();
        inventory.addWeapon(weapon);//variable de entrada el weapon(Tipo Weapon)
        assertTrue(weaponListSize < Inventory.getWeapons().size());//comprobar el tamaño de la lista despues de añadir un objeto a dicha lista
        assertEquals(weapon, Inventory.findWeapon(name));
    }

    @Test
    public void addArmorTest() {

        String name = "nameAttackDefense";
        Armor armor = EquipmentFactory.createArmor(name, 1, 1);
        int armorListSize = Inventory.getArmors().size();
        inventory.addArmor(armor);//variable de entrada el armor(Tipo Armor)
        assertTrue(armorListSize < Inventory.getArmors().size());//comprobar el tamaño de la lista despues de añadir un objeto a dicha lista
        assertEquals(armor, Inventory.findArmor(name));
    }

    @Test
    public void findWeaponTest() {
        String name = "Longsword";//Longsword existe en la base de datos(Inventory.dat)
        Weapon weapon = Inventory.findWeapon(name);//variable de entrada un string
        assertNotNull(weapon);//Comprobar que el weapon no null
        assertEquals(name, weapon.getName());
    }

    @Test
    public void findArmorTest() {
        
        String name = "Bronze Armor";//Bronze Armor existe en la base de datos(Inventory.dat)
        Armor armor = Inventory.findArmor(name);//variable de entrada un string
        assertNotNull(armor);//Comprobar que el weapon no null
        assertEquals(name, armor.getName());

        //caso de prueba: Con un name de armor o weapon no definido en el inventory.dat
    }

    @Test
    public void deleteWeaponTest() {
        Weapon weapon = EquipmentFactory.createWeapon("nameAttackDefense", 1, 1, 1);
        inventory.addWeapon(weapon);// Añadir un arma primero
        int weaponListSize = Inventory.getWeapons().size();
        Inventory.deleteWeapon(weapon);// Eliminar el arma
        assertTrue(weaponListSize > Inventory.getWeapons().size());// Comprobar que el tamaño de la lista ha disminuido
        assertNull(Inventory.findWeapon(weapon.getName()));// Comprobar que el arma ya no está en el inventario
    }

    @Test
    public void deleteArmorTest() {
        Armor armor = EquipmentFactory.createArmor("nameAttackDefense", 1, 1);
        inventory.addArmor(armor);// Añadir una armadura primero
        int armorListSize = Inventory.getArmors().size();
        Inventory.deleteArmor(armor);// Eliminar la armadura
        assertTrue(armorListSize > Inventory.getArmors().size());// Comprobar que el tamaño de la lista ha disminuido
        assertNull(Inventory.findArmor(armor.getName()));// Comprobar que la armadura ya no está en el inventario
    }

}
