package Character.Equipment;

import java.io.Serializable;

public class Armor implements Serializable {
    private String name;
    private int modAttack;
    private int modDefense;

    public Armor() {
    }

    public Armor(String name, int modDefense) {
        this.name = name;
        this.modDefense = modDefense;
    }

    public Armor(String name, int modAttack, int modDefense) {
        this.name = name;
        this.modAttack = modAttack;
        this.modDefense = modDefense;
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

}
