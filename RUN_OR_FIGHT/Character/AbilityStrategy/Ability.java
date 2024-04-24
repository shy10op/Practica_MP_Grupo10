package Character.AbilityStrategy;

import java.io.Serializable;
import java.util.Random;

import Character.AbilityStrategy.AbilityStrategy.Discipline;
import Character.AbilityStrategy.AbilityStrategy.Don;
import Character.AbilityStrategy.AbilityStrategy.Talent;
import Character.CharacterEspecific.Hunter;
import Character.CharacterEspecific.Vampire;
import Character.CharacterEspecific.Werewolf;

public class Ability {

    public static class WereWolfAbility implements Don, Serializable {
        public void don(Werewolf werewolf) {
            int power = werewolf.getPower();    
            int rage = werewolf.getRage();
            if (rage >= 0) {
                int ragePower = werewolf.getPower() + werewolf.getRage();
                werewolf.setPower(ragePower);
                rage--;
            } else {
                werewolf.setPower(power);
            }
        }
    }

    public static class VampireAbility implements Discipline, Serializable {
        public void discipline(Vampire vampire) {
            Random rand = new Random();
            int blood = rand.nextInt(vampire.getBlood());
            int hp = vampire.getHealth();
            int power = vampire.getPower();

            if (hp > 0 && hp > blood) {
                vampire.setHealth(hp - blood);
                vampire.setPower(power + blood);
            } else {
                vampire.setPower(power);
            }

        }
    }

    public static class HunterAbility implements Talent, Serializable {
        public void talent(Hunter hunter) {
            int ragePower = hunter.getPower() + hunter.getWillpower();
            hunter.setPower(ragePower);
        }

    }

}
