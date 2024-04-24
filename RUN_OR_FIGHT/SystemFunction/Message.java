package SystemFunction;

import java.util.ArrayList;

import Database.Initdata;
import User.User;

public class Message {

    public static void sendCombatToAdmin(Combate combate) {
        ArrayList<Combate> combates = Initdata.getCombates();
        User challenged = combate.getChallenged();
        User challenger = combate.getChallenger();
        if (challenged != null) {
            if (challenged.getAccountStatus()) {
                System.out.println("Combat sended: " + challenged.getNick());// destino
                System.out.println("You: " + challenger.getNick() + " vs Rival: " + challenged.getNick());
                combates.add(combate);
            } else {
                System.out.println("Destination User is already banned");
            }
        } else {
            System.out.println("Destination user not found");
        }
    }

    public static void sendCombatToChallenged(Combate combate) {
        if (combate != null) {
            User challenged = combate.getChallenged();
            challenged.setCombate(combate);
            Initdata.saveUsersToFile();
        } else {
            System.out.println("Combate not found");
        }

    }

    public static Combate receiveCombat(User user) {
        Combate combate = user.getCombate();
        if (combate == null) {
            System.out.println("XXXXXXXXX");
            return null;
        }
        if (combate.getChallenger() != null) {
            System.out.println("Combat received: " + combate.getChallenger().getNick());
            return combate;
        } else {
            System.out.println("No combat received");
            return null;
        }
    }

}
