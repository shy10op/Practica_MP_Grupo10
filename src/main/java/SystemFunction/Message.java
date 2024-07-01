package SystemFunction;

import Database.Initdata;
import User.User;

import java.util.ArrayList;

public class Message {

    public static void sendCombatToAdmin(Combate combate) { //primer paso
        ArrayList<Combate> combates = Initdata.getCombateList();
        User challenged = combate.getChallenged();
        User challenger = combate.getChallenger();
        if (challenger.equals(challenged)) {
            System.out.println("You cant challenge yourself");
        } else if (challenged != null) {
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

    public static void sendCombatToChallenged(Combate combate) {//Admin valida el combate y llama esta funcion para enviar al destino
        if (combate != null) {
            User challenged = combate.getChallenged();
            challenged.setCombate(combate);
        } else {
            System.out.println("Combate not found");
        }

    }

    public static Combate receiveCombat(User user) {//el destino recibe el combate
        Combate combate = user.getCombate();
        if (combate == null) {
            System.out.println("Combat Null");
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
