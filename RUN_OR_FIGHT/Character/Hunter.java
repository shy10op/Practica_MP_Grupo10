package Character;

import java.io.Serializable;

import Character.Equipment.Armor;
import Character.Equipment.Weapon;

public class Hunter extends CharacterFactory implements Serializable, CharacterCreator{
    private int willpoower;
    private Ability talent;

    public Hunter(String name, Weapon weapon, Weapon activeWeapon, Armor armor, Armor armorActive, Minion minion,
            int gold, int hp, int power, int strength) {
        super(name, weapon, activeWeapon, armor, armorActive, minion, gold, hp, power, strength);
    }

    @Override //rewrite the interface method CharacterCreator
    public void handleCharacter(Character character) {
        Hunter hunter = new Hunter(name, weapon, activeWeapon, armor, armorActive, minion, gold, hp, power, strength);
    }

    //Getters and setters
    public int getWillpoower() {
        return willpoower;
    }

    public void setWillpoower(int willpoower) {
        this.willpoower = willpoower;
    }

    public Ability getTalent() {
        return talent;
    }

    public void setTalent(Ability talent) {
        this.talent = talent;
    }    
}
