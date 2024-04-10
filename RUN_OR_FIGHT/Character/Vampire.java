package Character;

import java.io.Serializable;

import Character.Equipment.Armor;
import Character.Equipment.Weapon;

public class Vampire implements Serializable, CharacterCreator{
    private int blood;
    private int age;
    private Ability discipline;

    public Vampire(String name, Weapon weapon, Weapon activeWeapon, Armor armor, Armor armorActive, Minion minion,
    int gold, int hp, int power, int strength, int blood, int age, Ability discipline) {
        super(name, weapon, activeWeapon, armor, armorActive, minion, gold, hp, power, strength);
        this.blood = blood;
        this.age = age; 
        this.discipline = discipline;
    }

    public Vampire(Character character, int blood, int age, Ability discipline){
        this.character = character; 
        this.blood = blood;
        this.age = age; 
        this.discipline = discipline;

    }

    @Override //rewrite the interface method CharacterCreator
    public void handleCharacter(Character character) {
        Vampire vampire = new Vampire(minion, gold, hp, power, strength);
    }

    //Getters and setters
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
    
    

}
