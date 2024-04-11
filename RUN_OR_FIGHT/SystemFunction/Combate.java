package SystemFunction;

import java.io.Serializable;

import User.User;
import Character.Character;

public class Combate implements Serializable {
    private User Chanllenger;
    private User Chanllenged;
    private int amount;
    private String result;

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

    public User getChanllenger() {
        return Chanllenger;
    }

    public void setChanllenger(User chanllenger) {
        Chanllenger = chanllenger;
    }

    public User getChanllenged() {
        return Chanllenged;
    }

    public void setChanllenged(User chanllenged) {
        Chanllenged = chanllenged;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static void initialCombat(Combate combate) {
        Character challenger = combate.getChanllenger().getPlayer().getCharacter();
        Character challenged = combate.getChanllenged().getPlayer().getCharacter();

        int challengerAuxHP = challenger.getHealth();
        int challengedAuxHP = challenged.getHealth();

        int round = 1;
        while (challengerAuxHP > 0 && challengedAuxHP > 0) {
            System.out.println("Round " + round + ":");

            int damageToChallenged = calculateDamage(challenger, challenged);
            challengedAuxHP -= damageToChallenged;
            System.out.println(
                    challenger.getName() + " attacks " + challenged.getName() + " causing " + damageToChallenged
                            + " damage.");

            if (challengedAuxHP <= 0) {
                System.out.println(challenged.getName() + " has been defeated!");
                System.out.println("You lost " + combate.getAmount() + " gold");
                challenger.setGold(challenger.getGold() + combate.getAmount());
                challenged.setGold(challenged.getGold() - combate.getAmount());
                combate.getChanllenged().setCombate(null);
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
                combate.getChanllenged().setCombate(null);
                break;
            }

            round++;
        }
    }

    private static int calculateDamage(Character attacker, Character defender) {
        int attackPower = attacker.getPower()
                + (attacker.getWeapon() != null ? attacker.getWeapon().getModAttack() : 0);
        int defensePower = defender.getArmor() != null ? defender.getArmor().getModDefense() : 0;
        int damage = attackPower - defensePower;
        return Math.max(damage, 0);
    }
}
