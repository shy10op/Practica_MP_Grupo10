package Character;

public class Character {
    private String name;
    private int gold;
    private int health;
    private int attack;

    //Basic Constructors
    private Character(String name, int gold, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }



    // Getters and setters
    
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
}