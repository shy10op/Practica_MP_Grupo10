package Character;

import java.io.Serializable;
import javax.management.RuntimeErrorException;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import Character.Character;

public class CharacterFactory implements Serializable {
    public static Character createCharacter(String name, int gold, int health, int attack, String type,
            Inventory inventory, Ability kind, Boolean mod) {
        switch (type.toLowerCase()) {
            case "hunter":
                return new Hunter(name, gold, health, attack, inventory, kind, mod);
            case "vampire":
                return new Vampire(name, gold, health, attack, inventory, kind, mod);
            case "werewolf":
                return new Werewolf(name, gold, health, attack, inventory, kind, mod);
            default:
                throw new IllegalArgumentException("Tipo de personaje no reconocido: " + type);
        }
    }
}
