package Character.Equipment;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {
    private static ArrayList<Weapon> weapons;
    private static ArrayList<Armor> armors;

    public Inventory() {
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addArmor(Armor armor) {
        armors.add(armor);
    }

    public static void deleteWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    public static void deleteArmor(Armor armor) {
        armors.remove(armor);
    }

    public static Weapon findWeapon(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(name)) {
                return weapon;
            }
        }
        return null;
    }

    public static Armor findArmor(String name) {
        for (Armor armor : armors) {
            if (armor.getName().equals(name)) {
                return armor;
            }
        }
        return null;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

}
