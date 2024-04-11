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

    // Método para mostrar los jugadores utilizando AdminTools
    public void showPlayers() {
        adminTools.showPlayers();
    }

    // Método para buscar un jugador por su nick utilizando AdminTools
    public Player findPlayerByNick(String nick) {
        return adminTools.findPlayerByNick(nick);
    }

    // Método para modificar los atributos de un personaje utilizando AdminTools
    public void modifyCharacterAttributes(String nick, String attributeName, Object newValue) {
        adminTools.modifyCharacterAttributes(nick, attributeName, newValue);
    }

    // Método para obtener la lista de jugadores utilizando AdminTools
    public List<Player> getPlayerList() {
        return adminTools.getPlayerList();
    }

    // Método para actualizar la lista de jugadores en AdminTools
    public void updatePlayerList() {
        //adminTools.setPlayerList(Initdata.getPlayers());
    }
}
