package Character;

import Character.Equipment.Inventory;
import java.io.Serializable;

public class Werewolf extends Character implements Serializable, CharacterCreator {
    private int rage;
    private Ability don;

    public Werewolf(String name, int gold, int health, int attack, Inventory inventory, Ability kind, Boolean mod) {
        super(name, gold, health, attack, "werewolf", inventory, kind, mod);
    }

    public Werewolf(int rage, Ability don, String name, int gold, int health, int attack, Inventory inventory,
            Ability kind, Boolean mod) {
        super(name, gold, health, attack, "werewolf", inventory, kind, mod);
        this.rage = rage;
        this.don = don;
    }

    // Getters and setters
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

    @Override
    public void handleCharacter(Character character) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCharacter'");
    }
}
