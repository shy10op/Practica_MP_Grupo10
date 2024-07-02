package Character.AbilityStrategy;

import Character.AbilityStrategy.AbilityStrategy.Discipline;
import Character.AbilityStrategy.AbilityStrategy.Don;
import Character.AbilityStrategy.AbilityStrategy.Talent;
import Character.CharacterEspecific.Hunter;
import Character.CharacterEspecific.Vampire;
import Character.CharacterEspecific.Werewolf;

import java.io.Serializable;
import java.security.SecureRandom;

public class Ability implements Serializable {

    private static final SecureRandom rand = new SecureRandom();

    public static class WereWolfAbility implements Don, Serializable {

        @Override
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

        @Override
        public void discipline(Vampire vampire) {//0-20
            int blood = rand.nextInt(vampire.getBlood() + 1) + 1;
            int hp = vampire.getHealth();//hp que tiene ahora el vampiro
            int power = vampire.getPower();// el poder antes de usar la ha

            if (hp > 0 && hp > blood) {
                vampire.setHealth(hp - blood); 
                vampire.setPower(power + blood);
            } else {
                vampire.setPower(power);
            }
        }
    }

    public static class HunterAbility implements Talent, Serializable {

        @Override
        public void talent(Hunter hunter) {
            int ragePower = hunter.getPower() + hunter.getWillpower();
            hunter.setPower(ragePower);
        }

    }

}
