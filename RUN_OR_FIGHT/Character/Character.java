package Character;

import java.io.Serializable;

import Character.Equipment.Armor;
import Character.Equipment.Weapon;

public abstract class Character implements Serializable {
    protected String name;
    protected int gold;
    protected int health;
    protected int power;
    protected String type;

    protected Weapon weapon;
    protected Armor armor;

    public Character() {
    }

    public Character(String name, int gold, int health, int power) {
        this.name = name;
        this.gold = gold;
        this.health = health;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

}