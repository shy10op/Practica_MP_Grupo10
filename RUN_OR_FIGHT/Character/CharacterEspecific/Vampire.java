package Character.CharacterEspecific;

import Character.AbilityStrategy.AbilityStrategy.Discipline;
import Character.Character;

public class Vampire extends Character {
    private int blood;
    private int age;
    private Discipline discipline;

    public Vampire(String name, int gold, int hp, int power, int blood, int age) {
        super(name, gold, hp, power);
        this.blood = blood;
        this.age = age;
    }

    public int getBlood() {
        return blood;
    }

    public int getAge() {
        return age;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void useAbility() {
        if (discipline != null) {
            discipline.discipline(this);
        }
    }
}
