package Character.CharacterEspecific;

import Character.Character;

public class Vampire extends Character {
    private int blood;
    private int age;

    public Vampire(String name, int gold, int hp, int power, int blood, int age) {
        super(name, gold, hp, power);
        this.blood = blood;
        this.age = age;
    }
}
