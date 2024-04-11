package Character;

import java.io.Serializable;

import Character.Equipment.Inventory;

public abstract class Character implements Serializable {
    protected String name;
    protected int gold;
    protected int health;
    protected int power;
    protected String type;


    // Equipment
    protected Inventory inventory;

    protected Ability kind;
    protected Boolean mod;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}