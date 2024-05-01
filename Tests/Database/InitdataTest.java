package Database;

import Character.Equipment.Inventory;
import SystemFunction.Combate;
import User.User;
import Character.Equipment.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import User.Player;


import static org.junit.jupiter.api.Assertions.*;

class InitdataTest {
    private Inventory inventory;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException{


        Field usersField = Initdata.class.getDeclaredField("users");
        usersField.setAccessible(true);
        usersField.set(null, new ArrayList<User>());

        Field inventoriesField = Initdata.class.getDeclaredField("inventories");
        inventoriesField.setAccessible(true);
        inventoriesField.set(null, new Inventory());

        Field combatesField = Initdata.class.getDeclaredField("combates");
        combatesField.setAccessible(true);
        combatesField.set(null, new ArrayList<Combate>());

        Initdata.startInitData();
        inventory = Initdata.getInventories(); 
        inventory.getWeapons().clear();
        inventory.getArmors().clear();
        Initdata.generateInitialInventories();
    }

    @AfterEach
    public void teardown() throws NoSuchFieldException, IllegalAccessException {
        // Restablecer los campos estáticos a su estado inicial
        Field usersField = Initdata.class.getDeclaredField("users");
        usersField.setAccessible(true);
        usersField.set(null, new ArrayList<User>());

        Field inventoriesField = Initdata.class.getDeclaredField("inventories");
        inventoriesField.setAccessible(true);
        inventoriesField.set(null, new Inventory());

        Field combatesField = Initdata.class.getDeclaredField("combates");
        combatesField.setAccessible(true);
        combatesField.set(null, new ArrayList<Combate>());
    }



    @Test
    public void testGenerateBotsWithEmptyUsers() throws Exception{

        Initdata.generateBots();

        Field usersField = Initdata.class.getDeclaredField("users");
        usersField.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) usersField.get(null);

        assertEquals(11,users.size() );

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (i < 10) { // Verifica los detalles de los bots
                String botIndex = Integer.toString(i);
                assertEquals("Bot" + botIndex, user.getNick());
                assertEquals("12345", user.getPassword());
                assertEquals("player", user.getRole());
                assertEquals("vampire", user.getPlayer().getCharacter().getType());
            } else { // Verifica los detalles del admin
                assertEquals("AdminBot", user.getNick());
                assertEquals("admin", user.getRole());
            }
        }
    }

    @Test
    public void testGenerateInitialInventories() throws Exception{

        Field inventoriesField = Initdata.class.getDeclaredField("inventories");
        inventoriesField.setAccessible(true);
        Inventory inventories = (Inventory) inventoriesField.get(null);
        assertTrue(inventories.getWeapons().isEmpty() && inventories.getArmors().isEmpty());

        Initdata.generateInitialInventories();


        assertEquals(0, inventory.getWeapons().size());
        assertEquals(0, inventory.getArmors().size());

//        assertEquals("Longsword", inventory.getWeapons().get(0).getName());
//        assertEquals("Short Sword", inventory.getWeapons().get(1).getName());
//        assertEquals("One-handed axe", inventory.getWeapons().get(2).getName());
//        assertEquals("Axe", inventory.getWeapons().get(3).getName());
//        assertEquals("Spear", inventory.getWeapons().get(4).getName());
//
//        assertEquals("Bronze Armor", inventory.getArmors().get(0).getName());
//        assertEquals("Aluminum Armor", inventory.getArmors().get(1).getName());
//        assertEquals("Silver Armor", inventory.getArmors().get(2).getName());
//        assertEquals("Gold Armor", inventory.getArmors().get(3).getName());
//        assertEquals("Titanium Armor", inventory.getArmors().get(4).getName());

    }

    @Test
    public void testGenerateRandomCombates() throws Exception {
        List<User> testUsers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User("Bot" + i ,"12345","BotName" + i ,"player");
            user.setPlayer(new Player());
            testUsers.add(user);
        }

        Method method = Initdata.class.getDeclaredMethod("generateRandomCombates");
        method.setAccessible(true);

        Field usersField = Initdata.class.getDeclaredField("users");
        usersField.setAccessible(true);
        usersField.set(null, testUsers);

        Field combatesField = Initdata.class.getDeclaredField("combates");
        combatesField.setAccessible(true);
        combatesField.set(null, new ArrayList<Combate>());
        method.invoke(null);

        ArrayList<Combate> combates = (ArrayList<Combate>) combatesField.get(null);
        assertFalse(combates.isEmpty());

        for (Combate combate : combates) {
            assertNotNull(combate.getChallenger());
            assertNotNull(combate.getChallenged());
            assertTrue(combate.getAmount() > 0);
        }
        // Numero correcto de combates generados
        int expectedCombatesSize = testUsers.size() / 2;
        assertEquals(expectedCombatesSize, combates.size());
    }

    @Test
    public void testFileCreation() {
        assertNotNull(Initdata.getUsers());
        assertNotNull(Initdata.getInventories());
        assertNotNull(Initdata.getCombates());
    }



}
