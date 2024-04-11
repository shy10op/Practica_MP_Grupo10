package Character;

import java.io.Serializable;
import Character.Equipment.Inventory;

public class Hunter extends Character implements Serializable, CharacterCreator {
    private int willpoower;
    private Ability talent;

    public Hunter() {
    }

    public Hunter(String name, int gold, int health, int attack, String type, Inventory inventory, Ability kind,
            Boolean mod) {
        super(name, gold, health, attack, type, inventory, kind, mod);
    }

    public Hunter(int willpoower, Ability talent, String name, int gold, int health, int attack, String type,
            Inventory inventory, Ability kind, Boolean mod) {
        super(name, gold, health, attack, type, inventory, kind, mod);
        this.willpoower = willpoower;
        this.talent = talent;
    }

    public Hunter(String name, int gold, int health, int attack, Inventory inventory, Ability kind, Boolean mod) {
        super(name, gold, health, attack, "hunter", inventory, kind, mod);
    }

    // Getters and setters
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

    @Override
    public void handleCharacter(Character character) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCharacter'");
    }
}
