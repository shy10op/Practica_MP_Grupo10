package Character;

import java.io.Serializable;
import Character.Equipment.Inventory;

public class Vampire extends Character implements Serializable, CharacterCreator {
    private int blood;
    private int age;
    private Ability discipline;

    public Vampire(String name, int gold, int health, int attack, Inventory inventory, Ability kind, Boolean mod) {
        super(name, gold, health, attack, "vampire", inventory, kind, mod);

    }

    public Vampire(int blood, int age, Ability discipline, String name, int gold, int health, int attack,
            Inventory inventory, Ability kind, Boolean mod) {
        super(name, gold, health, attack, "vampire", inventory, kind, mod);
        this.blood = blood;
        this.age = age;
        this.discipline = discipline;
    }

    // Getters and setters
    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Ability getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Ability discipline) {
        this.discipline = discipline;
    }

    @Override
    public void handleCharacter(Character character) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCharacter'");
    }

}
