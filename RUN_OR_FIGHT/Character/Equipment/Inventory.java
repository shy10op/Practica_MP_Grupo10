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

    public void addWeapon(Weapon arma) {
        weapons.add(arma);
    }

    public void addArmor(Armor armor) {
        armors.add(armor);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

}
