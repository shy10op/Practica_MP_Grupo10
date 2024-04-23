package SystemFunction;

import java.util.ArrayList;

import Database.Initdata;
import User.User;

public class Message {

    public static void sendCombatToAdmin(Combate combate) {
        ArrayList<Combate> combates = Initdata.getCombates();
        User chanllenged = combate.getChallenged();
        if (chanllenged != null) {
            System.out.println("Combat sended: " + chanllenged.getNick()); // destino
            combates.add(combate);
        } else {
            System.out.println("Destination user not found");
        }
    }

    public static void sendCombatToChallenged(Combate combate) {
        User challenged = combate.getChallenged();
        challenged.setCombate(combate);
    }

    public static Combate receiveCombat(User user) {
        Combate combate = user.getCombate();
        if (combate == null) {
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
