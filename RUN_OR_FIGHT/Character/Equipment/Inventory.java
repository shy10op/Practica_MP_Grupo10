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

    // Métodos para obtener las listas de armas y armors
    public ArrayList<Weapon> obtenerArmas() {
        return weapons;
    }

    public ArrayList<Armor> obtenerArmaduras() {
        return armors;
    }

    // Metodos específicos
}
