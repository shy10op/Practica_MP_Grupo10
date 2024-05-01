package User;

import Database.Initdata;
import SystemFunction.Combate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeAll
    public static void setup() {
        // Inicializar los bots antes de ejecutar las pruebas.
        Initdata.generateBots();
    }

    @Test
    public void testFindUser() {
        User user = new User("Bot1","12345", "BotName1","player");
        User actualUser = User.findUser("Bot1");
        assertEquals(user.getNick(), actualUser.getNick());
    }

    @Test
    public void testDeleteUser() {
        User user = new User("Bot1","12345", "BotName1","player");
        User actualUser = User.findUser("Bot1");
        assertEquals(user.getNick(), actualUser.getNick());
    }

    @Test
    public void testIsLogged() {
        User user = new User();
        user.setLogged(true);
        assertTrue(user.isLogged());
    }

    @Test
    public void testSetLogged() {
        User user = new User();
        user.setLogged(true);
        assertTrue(user.isLogged());
    }

    @Test
    public void testGetNick(){
        User user = new User("Bot1","12345", "BotName1","player");
        assertEquals("Bot1",user.getNick());
    }

    @Test
    public void testSetNick(){
        User user = new User();
        user.setNick("Bot1");
        assertEquals("Bot1",user.getNick());
    }

    @Test
    public void testGetPassword(){
        User user = new User("Bot1","12345", "BotName1","player");
        assertEquals("12345",user.getPassword());
    }

    @Test
    public void testSetPassword(){
        User user = new User();
        user.setPassword("12345");
        assertEquals("12345",user.getPassword());
    }

    @Test
    public void testGetName(){
        User user = new User("Bot1","12345", "BotName1","player");
        assertEquals("BotName1",user.getName());
    }

    @Test
    public void testSetName(){
        User user = new User();
        user.setName("BotName1");
        assertEquals("BotName1",user.getName());
    }

    @Test
    public void testGetRole(){
        User user = new User("Bot1","12345", "BotName1","player");
        assertEquals("player",user.getRole());
    }

    @Test
    public void testSetRole(){
        User user = new User();
        user.setRole("player");
        assertEquals("player",user.getRole());
    }

    @Test
    public void testSetAccountStatus() {
        User user = new User();
        user.setAccountStatus(true);
        assertTrue(user.getAccountStatus());
    }

    @Test
    public void testGetAccountStatus(){
        User user = new User("Bot1","12345", "BotName1","player");
        assertTrue(user.getAccountStatus());
    }

    @Test
    public void testGetLogged(){
        User user = new User("Bot1","12345", "BotName1","player");
        assertFalse(user.getLogged());
    }

    @Test
    public void testSetPlayer(){
        User user = new User("Bot1","12345", "BotName1","player");
        Player player = new Player();
        user.setPlayer(player);
        assertEquals(player ,user.getPlayer());
    }

    @Test
    public void testSetAdmin(){
        User user = new User("AdminBot","12345","AdminBot","admin");
        Admin admin = new Admin();
        user.setAdmin(admin);
        assertEquals(admin ,user.getAdmin());
    }

    @Test
    public void testGetCombate(){
        User user1 = new User();
        User user2 = new User();
        Combate combate = new Combate(user1, user2, 100);
        user1.setCombate(combate);
        assertEquals(combate ,user1.getCombate());
    }

    @Test
    public void testSetCombate(){
        User user1 = new User();
        User user2 = new User();
        Combate combate = new Combate(user1, user2, 100);
        user1.setCombate(combate);
        assertEquals(combate ,user1.getCombate());
    }
}
