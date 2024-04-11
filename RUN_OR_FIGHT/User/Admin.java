package User;

//import Database.Initdata;
import java.util.List;

public class Admin extends User {

    private AdminTools adminTools;

    public Admin(String nick, String password, String name) {
        super(nick, password, name, "admin");
        // Inicializar AdminTools con la lista de jugadores obtenida de Initdata
        //this.adminTools = new AdminTools(Initdata.getPlayers());
    }


    public void showPlayers() {
        adminTools.showPlayers();
    }

    public Player findPlayerByNick(String nick) {
        return adminTools.findPlayerByNick(nick);
    }

    public void modifyCharacterAttributes(String nick, String attributeName, Object newValue) {
        adminTools.modifyCharacterAttributes(nick, attributeName, newValue);
    }

    public List<Player> getPlayerList() {
        return adminTools.getPlayerList();
    }


    public void updatePlayerList() {
        //adminTools.setPlayerList(Initdata.getPlayers());
    }
}
