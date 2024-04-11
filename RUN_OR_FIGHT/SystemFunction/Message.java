package SystemFunction;

import User.User;

public class Message {

    public static void sendCombat(Combat combat) {
        User chanllenged = combat.getChanllenged();
        if (chanllenged != null) {
            System.out.println("Combat sended: " + chanllenged.getNick()); // destino
            chanllenged.setCombate(combat); // ajustar el combatStatus con el nombre de Chanllenger
        } else {
            System.out.println("Destination user not found");
        }
    }

    public static Combat receiveCombat(User user) {

        Combat combat = user.getCombate();
        if (combat == null) {
            return null;
        }
        if (combat.getChanllenger() != null) {
            System.out.println("Combat received: " + combat.getChanllenger().getNick());
            return combat;
        } else {
            System.out.println("No combat received");
            return null;
        }
    }

}
