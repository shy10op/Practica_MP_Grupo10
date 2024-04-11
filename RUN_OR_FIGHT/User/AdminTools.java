package User;

import java.util.List;

public class AdminTools {
    private List<Player> playerList;

    public AdminTools(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void showPlayers() {
        for (Player player : playerList) {
            System.out.println(player.getNick() + " - " + player.getName());
        }
    }

    public Player findPlayerByNick(String nick) {
        for (Player player : playerList) {
            if (player.getNick().equals(nick)) {
                return player;
            }
        }
        return null;
    }

    public void modifyCharacterAttributes(String nick, String attributeName, Object newValue) {
        Player player = findPlayerByNick(nick);
        if (player != null && player.getCharacter() != null) {
            switch (attributeName.toLowerCase()) {
                case "name":
                    player.getCharacter().setName((String) newValue);
                    break;
                case "power":
                    player.getCharacter().setPower((Integer) newValue);
                    break;
                case "health":
                    player.getCharacter().setHealth((Integer) newValue);
                    break;
                default:
                    System.out.println("Atributo no válido.");
                    break;
            }
        } else {
            System.out.println("Jugador no encontrado o sin personaje.");
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

}
