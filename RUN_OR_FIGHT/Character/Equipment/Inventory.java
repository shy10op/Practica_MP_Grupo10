package Character.Equipment;

import java.util.ArrayList;

import java.io.Serializable;

public class Inventory implements Serializable {
    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armors;

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

    public void deleteWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    public void deleteArmor(Armor armor) {
        armors.remove(armor);
    }

    public Weapon findWeapon(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(name)) {
                return weapon;
            }
        }
        return null;
    }

    public Armor findArmor(String name) {
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
