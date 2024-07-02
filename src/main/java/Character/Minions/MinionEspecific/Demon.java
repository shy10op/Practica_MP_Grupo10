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
    public String getPactDescription() {
        return pactDescription;
    }

    public void setPactDescription(String pactDescription) {
        this.pactDescription = pactDescription;
    }

    public boolean isPact() {
        return pact;
    }

    public void setPact(boolean pact) {
        this.pact = pact;
    }
}