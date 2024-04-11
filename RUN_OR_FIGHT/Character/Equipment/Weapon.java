package Character.Equipment;

import java.io.Serializable;

public class Weapon implements Serializable {

    private String name;
    private int modAttack;
    private int modDefense;
    private int spaceHand;

    public Weapon() {
    }

    // Arma con modificador de ataque
    public Weapon(String name, int modAttack, int spaceHand) {
        this.name = name;
        this.modAttack = modAttack;
        this.spaceHand = spaceHand;
    }

    // Arma con modificador de ataque y defensa
    public Weapon(String name, int modAttack, int modDefense, int spaceHand) {
        this.name = name;
        this.modAttack = modAttack;
        this.modDefense = modDefense;
        this.spaceHand = spaceHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModAttack() {
        return modAttack;
    }

    public void setModAttack(int modAttack) {
        this.modAttack = modAttack;
    }

    public int getModDefense() {
        return modDefense;
    }

    public void setModDefense(int modDefense) {
        this.modDefense = modDefense;
    }

    public int getSpaceHand() {
        return spaceHand;
    }

    public void setSpaceHand(int spaceHand) {
        this.spaceHand = spaceHand;
    }

}
