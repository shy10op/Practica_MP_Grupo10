package Character.CharacterEspecific;

import Character.AbilityStrategy.AbilityStrategy.Don;
import Character.Character;

public class Werewolf extends Character {
    private int rage;
    private transient Don don;

    public Werewolf(String name, int gold, int hp, int power, int rage) {
        super(name, gold, hp, power);
        this.rage = rage;
    }

    public int getRage() {
        return rage;
    }

    public void useAbility() {
        don.don(this);
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public void setDon(Don don) {
        this.don = don;
    }
}
