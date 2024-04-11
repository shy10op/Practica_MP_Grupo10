package SystemFunction;

import User.User;

public class Message {

    public static void sendCombat(String nick, String destinationNick) {
        User destinationUser = User.findUser(destinationNick);
        if (destinationUser != null) {
            System.out.println("Combat sended: " + destinationUser.getNick()); // destino
            destinationUser.setCombatStatus(nick); // origen
        } else {
            System.out.println("Destination user not found");
        }
    }

    public static User receiveCombat(User user) {
        String rivalNick = user.getCombatStatus();
        if (rivalNick != null) {
            // con esto puedes conseguir el usuario origen con la funcion findUser(nick) y
            // devuelve un User
            // y apartir de eso sacar los datos de ese usuario para realizar el combate
            System.out.println("Combat received: " + rivalNick);
            return User.findUser(rivalNick);
        } else {
            System.out.println("No combat received");
            return null;
        }
    }

}