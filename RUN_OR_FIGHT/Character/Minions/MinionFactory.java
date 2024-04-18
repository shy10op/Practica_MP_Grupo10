package Character.Minions;

import java.io.Serializable;

import Character.Minions.MinionEspecific.Demon;
import Character.Minions.MinionEspecific.Ghoul;
import Character.Minions.MinionEspecific.Human;
import Character.Minions.MinionEspecific.Human.Loyalty;

public class MinionFactory implements Serializable {
    public static Minion createMinion(String type, String name, int hp, Loyalty loyalty, Boolean pact,
            String description, int dependenceOnMaster) {
        switch (type) {
            case "human":
                return new Human(name, hp, loyalty);
            case "demon":
                return new Demon(name, hp, pact, description);
            case "ghoul":
                return new Ghoul(name, hp, dependenceOnMaster);
            default:
                throw new IllegalArgumentException("Unknow Minion type : " + type);
        }
    }
}
