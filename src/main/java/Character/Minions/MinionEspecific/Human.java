package Character.Minions.MinionEspecific;

import Character.Minions.Minion;

public class Human extends Minion {
    Loyalty loyalty;

    public Human(String name, int health, Loyalty loyalty) {
        super(name, health);
        this.loyalty = loyalty;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

    public enum Loyalty {
        LOW, NORMAL, HIGH
    }

}
