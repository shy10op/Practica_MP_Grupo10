package Character;

import Character.CharacterEspecific.Hunter;
import Character.CharacterEspecific.Vampire;
import Character.CharacterEspecific.Werewolf;

import java.io.Serializable;

public class CharacterFactory implements Serializable {
    public static Character createCharacter(String type, String name, int gold, int health, int power, int blood,
            int age, int rage) {
        switch (type) {
            case "hunter":
                return new Hunter(name, gold, health, power);
            case "vampire":
                return new Vampire(name, gold, health, power, blood, age);
            case "werewolf":
                return new Werewolf(name, gold, health, power, rage);
            default:
                return null;
        }
    }
}
