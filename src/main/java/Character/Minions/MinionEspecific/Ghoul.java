package Character.Minions.MinionEspecific;

import Character.Minions.Minion;

public class Ghoul extends Minion {
    int dependenceOnMaster;

    public Ghoul(String name, int health, int dependenceOnMaster) {
        super(name, health);
        if (dependenceOnMaster > 1 || dependenceOnMaster < 5) {
            this.dependenceOnMaster = dependenceOnMaster;
        } else {
            this.dependenceOnMaster = 5;
        }
    }
}
