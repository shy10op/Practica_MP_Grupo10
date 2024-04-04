package Character;

import java.io.Serializable;
import javax.management.RuntimeErrorException;

public class CharacterFactory implements Serializable{
    protected String name;
    protected Weapon weapon;
    protected Weapon activeWeapon; 
    protected Armor armor;
    protected Armor armorActive; 
    protected Minion minion;
    protected int gold;
    protected int hp;
    protected int power;
    protected int strength;

     //Kind of character
    public enum Kind {
        VAMPIRE, WEREWOLF, HUNTER
    }
    
    public CharacterFactory(String name, Weapon weapon, Weapon activeWeapon, Armor armor, Armor armorActive,
            Minion minion, int gold, int hp, int power, int strength) {
        this.name = name;
        this.weapon = weapon;
        this.activeWeapon = activeWeapon;
        this.armor = armor;
        this.armorActive = armorActive;
        this.minion = minion;
        this.gold = gold;
        this.hp = hp;
        this.power = power;
        this.strength = strength;
    }

    public CharacterCreator createCharacterHandle(Character.Kind kind){
        return switch (kind){
            case VAMPIRE -> new Vampire(vampire);
            case HUNTER -> new Hunter(hunter);
            case WEREWOLF -> new Werewolf(werewolf);
            default -> throw new RuntimeErrorException(null, "ERROR: No se ha podido crear un personaje");
        };
    }  
}
