package Character.Minions.MinionEspecific;

import Character.Minions.Minion;

public class Demon extends Minion {
    boolean pact;
    String pactDescription;

    public Demon(String name, int health, boolean pact, String pactDescription) {
        super(name, health);
        this.pact = pact;
        this.pactDescription = pactDescription;
    }
}