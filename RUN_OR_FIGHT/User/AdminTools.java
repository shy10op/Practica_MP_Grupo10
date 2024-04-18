package User;

import java.util.List;
import Character.Character;

public class AdminTools {
    private List<User> playerList;

    public AdminTools(List<User> playerList) {
        this.playerList = playerList;
    }

    public void showPlayers() {
        for (User user : playerList) {
            if (user instanceof Player) {
                Player player = (Player) user;
                System.out.println(player.getNick() + " - " + player.getName());
            }
        }
    }

    public Player findPlayerByNick(String nick) {
        for (User user : playerList) {
            if (user instanceof Player) {
                Player player = (Player) user;
                if (player.getNick().equals(nick)) {
                    return player;
                }
            }
        }
        return null;
    }

    public void modifyCharacterAttributes(String nick, String attributeName, Object newValue) {
        Player player = findPlayerByNick(nick);
        if (player != null && player.getCharacter() != null) {
            Character character = player.getCharacter();
            switch (attributeName.toLowerCase()) {
                case "name":
                    character.setName((String) newValue);
                    break;
                case "power":
                    character.setPower((Integer) newValue);
                    break;
                case "health":
                    character.setHealth((Integer) newValue);
                    break;
                default:
                    System.out.println("Invalid attribute");
                    break;
            }
        } else {
            System.out.println("Player not found or without chracter");
        }
    }

    public List<User> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<User> playerList) {
        this.playerList = playerList;
    }
}
