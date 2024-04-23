package Character.CharacterEspecific;

import Character.Character;
import Character.AbilityStrategy.AbilityStrategy.Talent;

public class Hunter extends Character {
    private int willpower;
    private Talent talent;

    public Hunter(String name, int gold, int hp, int power) {
        super(name, gold, hp, power);
        this.willpower = 3; // willpower initial is 3
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public void setTalento(Talent talento) {
        this.talent = talento;
    }

    public void useAbility() {
        talent.talent(this);
    }

}
