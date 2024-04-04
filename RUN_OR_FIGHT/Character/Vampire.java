package Character;

import java.io.Serializable;

public class Vampire extends CharacterFactory implements Serializable, CharacterCreator{
    private int blood;
    private int age;
    private Ability discipline;

    public Vampire(String name, Weapon weapon, Weapon activeWeapon, Armor armor, Armor armorActive, Minion minion,
            int gold, int hp, int power, int strength) {
        super(name, weapon, activeWeapon, armor, armorActive, minion, gold, hp, power, strength);
        //TODO Auto-generated constructor stub
    }

    @Override //rewrite the interface method CharacterCreator
    public void handleCharacter(Character character) {
        Vampire vampire = new Vampire(name, weapon, activeWeapon, armor, armorActive, minion, gold, hp, power, strength);
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
