package Character;

import java.io.Serializable;

import Character.Equipment.Armor;
import Character.Equipment.Weapon;

public class Werewolf extends CharacterFactory implements Serializable, CharacterCreator{
    private int rage;
    private Ability don;

    public Werewolf(String name, Weapon weapon, Weapon activeWeapon, Armor armor, Armor armorActive, Minion minion,
            int gold, int hp, int power, int strength, int rage, Ability don) {
        super(name, weapon, activeWeapon, armor, armorActive, minion, gold, hp, power, strength);
        this.rage = rage;
        this.don = don;
    }

    @Override //rewrite the interface method CharacterCreator
    public void handleCharacter(Character character) {
        Werewolf werewolf = new Werewolf(name, weapon, activeWeapon, armor, armorActive, minion, gold, hp, power, strength, rage, don);
    }

    //Getters and setters
    public int getRage() {
        return rage;
    }

    public Ability getDon() {
        return don;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public void setDon(Ability don) {
        this.don = don;
    }
}
