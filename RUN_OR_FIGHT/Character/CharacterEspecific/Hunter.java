package Character.CharacterEspecific;

import Character.Character;

public class Hunter extends Character {
    private int willpower;

    public Hunter(String name, int gold, int hp, int power) {
        super(name, gold, hp, power);
        this.willpower = 3; // willpower inicial es 3
    }
}
