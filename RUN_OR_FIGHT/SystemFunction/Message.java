package SystemFunction;

import User.User;

public class Message {

    public static void sendCombat(Combate combate) {
        User chanllenged = combate.getChanllenged();
        if (chanllenged != null) {
            System.out.println("Combat sended: " + chanllenged.getNick()); // destino
            chanllenged.setCombate(combate); // ajustar el combatStatus con el nombre de Chanllenger
        } else {
            System.out.println("Destination user not found");
        }
    }

    public static Combate receiveCombat(User user) {
        
        Combate combate = user.getCombate();
        if (combate == null) {
            return null;
        }
        if (combate.getChanllenger() != null) {
            System.out.println("Combat received: " + combate.getChanllenger().getNick());
            return combate;
        } else {
            System.out.println("No combat received");
            return null;
        }
    }

}
