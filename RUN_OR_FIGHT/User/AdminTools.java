package User;

import java.io.Serializable;
import java.util.List;
import Character.Character;

public class AdminTools implements Serializable {
    private List<User> playerList;

    public AdminTools(List<User> playerList) {
        this.playerList = playerList;
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
                    if (newValue instanceof Integer) {
                        character.setPower((Integer) newValue);
                    } else {
                        System.out.println("Invalid value for power attribute");
                    }
                    break;
                case "health":
                    if (newValue instanceof Integer) {
                        character.setHealth((Integer) newValue);
                    } else {
                        System.out.println("Invalid value for health attribute");
                    }
                    break;
                default:
                    System.out.println("Invalid attribute");
                    break;
            }
        } else {
            System.out.println("Player not found or without character");
        }
    }

    public List<User> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<User> playerList) {
        this.playerList = playerList;
    }
}
