package Character;

import java.io.Serializable;

import Character.Equipment.Inventory;

public abstract class Character implements Serializable {
    protected String name;
    protected int gold;
    protected int health;
    protected int attack;
    protected String type;

    // Equipment
    protected Inventory inventory;

    protected Ability kind;
    protected Boolean mod;

    public Character() {
    }

    public Character(String name, int gold, int health, int attack, String type, Inventory inventory, Ability kind,
            Boolean mod) {
        this.name = name;
        this.gold = gold;
        this.health = health;
        this.attack = attack;
        this.type = type;
        this.inventory = inventory;
        this.kind = kind;
        this.mod = mod;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Ability getKind() {
        return kind;
    }

    public void setKind(Ability kind) {
        this.kind = kind;
    }

    public Boolean getMod() {
        return mod;
    }

    public void setMod(Boolean mod) {
        this.mod = mod;
    }

}