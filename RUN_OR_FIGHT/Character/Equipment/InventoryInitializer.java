package Character.Equipment;

import java.io.*;

public class InventoryInitializer {
    public static void initializeGame(Inventory inventory) {
        Weapon weapon1 = EquipmentFactory.createWeapon("Longsword", 3, 1, 2);
        Weapon weapon2 = EquipmentFactory.createWeapon("Short Sword", 2, 0, 1);
        Weapon weapon3 = EquipmentFactory.createWeapon("One-handed axe", 2, 1, 1);
        Weapon weapon4 = EquipmentFactory.createWeapon("Axe", 3, 2, 2);
        Weapon weapon5 = EquipmentFactory.createWeapon("Spear", 2, 1, 2);
        inventory.addWeapon(weapon1);
        inventory.addWeapon(weapon2);
        inventory.addWeapon(weapon3);
        inventory.addWeapon(weapon4);
        inventory.addWeapon(weapon5);
        Armor armor1 = EquipmentFactory.createArmor("Bronze Armor", 0, 2);
        Armor armor2 = EquipmentFactory.createArmor("Aluminum Armor", 2, 1);
        Armor armor3 = EquipmentFactory.createArmor("Silver Armor", 1, 2);
        Armor armor4 = EquipmentFactory.createArmor("Gold Armor", 0, 2);
        Armor armor5 = EquipmentFactory.createArmor("Titanium Armor", 2, 3);
        inventory.addArmor(armor1);
        inventory.addArmor(armor2);
        inventory.addArmor(armor3);
        inventory.addArmor(armor4);
        inventory.addArmor(armor5);
    }

    public static void SaveInventory(Inventory inventory, String Initdata) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Initdata))) {
            oos.writeObject(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Inventory LoadInventory(String Initdata) {
        Inventory inventory = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Initdata))) {
            inventory = (Inventory) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return inventory;
    }
}
