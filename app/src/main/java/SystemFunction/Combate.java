package SystemFunction;

import Character.AbilityStrategy.Ability.HunterAbility;
import Character.AbilityStrategy.Ability.VampireAbility;
import Character.AbilityStrategy.Ability.WereWolfAbility;
import Character.Character;
import Character.CharacterEspecific.Hunter;
import Character.CharacterEspecific.Vampire;
import Character.CharacterEspecific.Werewolf;
import Database.Initdata;
import User.User;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Combate implements Serializable {
    private User Chanllenger;
    private User Chanllenged;
    private int amount;
    private String result;
    private static final SecureRandom random = new SecureRandom();

    public enum CharacterType {
        HUNTER,
        VAMPIRE,
        Werewolf
    }

    public Combate(User Chanllenger, User Chanllenged, int amount) {
        this.Chanllenger = Chanllenger;// origen
        this.Chanllenged = Chanllenged;// destino
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getChallenger() {
        return Chanllenger;
    }

    public void setChallenger(User chanllenger) {
        Chanllenger = chanllenger;
    }

    public User getChallenged() {
        return Chanllenged;
    }

    public void setChallenged(User chanllenged) {
        Chanllenged = chanllenged;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static void deleteCombate(Combate combate) {
        ArrayList<Combate> combates = Initdata.getCombateList();
        if (!combates.contains(combate)) {
            System.out.println("Combate not found in the Combate File");
        }

        boolean isRemoved = combates.remove(combate);
        if (isRemoved) {
            Initdata.saveCombatesToFile();
        } else {
            System.out.println("Failed to remove combate.");
        }
    }

    public static int initialCombat(Combate combate) {
        Character challenger = combate.getChallenger().getPlayer().getCharacter();
        Character challenged = combate.getChallenged().getPlayer().getCharacter();

        int challengerHP = challenger.getHealth();
        int challengedHP = challenged.getHealth();

        int challengerMinionsHP = 0;
        int challengedMinionsHP = 0;

        if (challenger.getMinion() != null) {
            challengerMinionsHP = challenger.getMinion().getHealth();
        }

        if (challenged.getMinion() != null) {
            challengedMinionsHP = challenged.getMinion().getHealth();
        }

        int challengerAuxHP = challengerHP + challengerMinionsHP;
        int challengedAuxHP = challengedHP + challengedMinionsHP;
        System.out.println("Chanllenger HP: " + challengerHP + ", minions HP: " + challengerMinionsHP);
        System.out.println("Chanllenged HP: " + challengedHP + ", minions HP: " + challengedMinionsHP);

        int round = 1;
        while (challengerAuxHP > 0 && challengedAuxHP > 0) {
            System.out.println("Round " + round + ":");
            System.out.println("Challenger HP :" + challengerAuxHP + "  Chanllenged HP :" + challengedAuxHP);

            int damageToChallenged = calculateDamage(challenger, challenged);
            challengedAuxHP -= damageToChallenged;

            if (random.nextDouble() < 0.5) {
                combatAbility(challenger);
                System.out.println(challenger.getName() + " now his power now is :" + challenger.getPower());
            }

            if (random.nextDouble() < 0.5) {
                combatAbility(challenged);
                System.out.println(challenged.getName() + " now his power is :" + challenged.getPower());
            }

            System.out.println(
                    challenger.getName() + " attacks " + challenged.getName() + " causing " + damageToChallenged
                            + " damage.");

            if (challengedAuxHP <= 0) {
                System.out.println(challenged.getName() + " has been defeated!");
                System.out.println("You lost " + combate.getAmount() + " gold");
                challenger.setGold(challenger.getGold() + combate.getAmount());
                challenged.setGold(challenged.getGold() - combate.getAmount());
                deleteMinion(challenged);
                break;
            }

            int damageToChallenger = calculateDamage(challenged, challenger);
            challengerAuxHP -= damageToChallenger;
            System.out.println(
                    challenged.getName() + " attacks " + challenger.getName() + " causing " + damageToChallenger
                            + " damage.");

            if (challengerAuxHP <= 0) {
                System.out.println(challenger.getName() + " has been defeated!");
                System.out.println("You earned " + combate.getAmount() + " gold");
                challenger.setGold(challenger.getGold() - combate.getAmount());
                challenged.setGold(challenged.getGold() + combate.getAmount());
                deleteMinion(challenger);
                break;
            }
            round++;
        }
        combate.getChallenged().setCombate(null);
        combate.getChallenger().setCombate(null);
        Initdata.saveCombatesToFile();
        return round;
    }

    private static int calculateDamage(Character attacker, Character defender) {
        int attackPower = attacker.getPower()
                + (attacker.getWeapon() != null ? attacker.getWeapon().getModAttack() : 0);
        int defensePower = defender.getArmor() != null ? defender.getArmor().getModDefense() : 0;
        int damage = attackPower - defensePower;
        return Math.max(damage, 0);
    }

    private static void deleteMinion(Character loser) {
        if (loser.getMinion() != null) {
            System.out.printf("%s minion %s is dead\n", loser.getName(), loser.getMinion().getName());
            loser.setMinion(null);
        } else {
            System.out.println("you don't have a minion\n");
        }
    }

    private static void combatAbility(Character character) {
        switch (character.getType()) {
            case "hunter":
                ((Hunter) character).setTalent(new HunterAbility());
                System.out.println(character.getName() + " used Willpower");
                ((Hunter) character).useAbility();
                break;
            case "vampire":
                ((Vampire) character).setDiscipline(new VampireAbility());
                System.out.println(character.getName() + " used Blood");
                ((Vampire) character).useAbility();
                break;
            case "werewolf":
                ((Werewolf) character).setDon(new WereWolfAbility());
                System.out.println(character.getName() + " used Rage");
                ((Werewolf) character).useAbility();
                break;
            default:
                System.out.println("Magikarp used Splash, and nothing happends");
        }
    }
}
