package Character.CharacterEspecific;

import Character.Character;

public class Werewolf extends Character {
    private int rage;

    public Werewolf(String name, int gold, int hp, int power, int rage) {
        super(name, gold, hp, power);
        this.rage = rage;
    }
}
