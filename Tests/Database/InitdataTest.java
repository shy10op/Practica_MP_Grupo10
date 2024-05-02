package Database;

import Character.Equipment.EquipmentFactory;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import SystemFunction.Combate;
import User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import User.Player;


import static org.junit.jupiter.api.Assertions.*;

class InitdataTest {
    private Inventory inventory;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {


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
    public void testGenerateBotsWithEmptyUsers() throws Exception {

        Initdata.generateBots();

        Field usersField = Initdata.class.getDeclaredField("users");
        usersField.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) usersField.get(null);

        assertEquals(11, users.size());

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
    public void testGenerateInitialInventories() throws Exception {

        Field inventoriesField = Initdata.class.getDeclaredField("inventories");
        inventoriesField.setAccessible(true);
        Inventory inventories = (Inventory) inventoriesField.get(null);
        assertFalse(inventories.getWeapons().isEmpty() && inventories.getArmors().isEmpty());

        Initdata.generateInitialInventories();


        assertEquals(5, inventory.getWeapons().size());
        assertEquals(5, inventory.getArmors().size());

        assertEquals("Longsword", inventory.getWeapons().get(0).getName());
        assertEquals("Short Sword", inventory.getWeapons().get(1).getName());
        assertEquals("One-handed axe", inventory.getWeapons().get(2).getName());
        assertEquals("Axe", inventory.getWeapons().get(3).getName());
        assertEquals("Spear", inventory.getWeapons().get(4).getName());

        assertEquals("Bronze Armor", inventory.getArmors().get(0).getName());
        assertEquals("Aluminum Armor", inventory.getArmors().get(1).getName());
        assertEquals("Silver Armor", inventory.getArmors().get(2).getName());
        assertEquals("Gold Armor", inventory.getArmors().get(3).getName());
        assertEquals("Titanium Armor", inventory.getArmors().get(4).getName());

    }

    @Test
    public void testGenerateRandomCombates() throws Exception {
        List<User> testUsers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User("Bot" + i, "12345", "BotName" + i, "player");
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

    @Test
    public void loadUsersFromFileTest() throws NoSuchFieldException, IllegalAccessException {
        Initdata.loadUsersFromFile();
        Field usersField = Initdata.class.getDeclaredField("users");
        usersField.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) usersField.get(null);

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void loadInventoriesFromFileTest() throws NoSuchFieldException,
            IllegalAccessException {

        Initdata.loadInventoriesFromFile();

        Field inventoriesField = Initdata.class.getDeclaredField("inventories");
        inventoriesField.setAccessible(true);
        Inventory inventories = (Inventory) inventoriesField.get(null);

        assertNotNull(inventories);
    }

    @Test
    public void TestLoadCombatesFromFile() throws NoSuchFieldException,
            IllegalAccessException {
        Initdata.loadCombatesFromFile();

        Field combatesField = Initdata.class.getDeclaredField("combates");
        combatesField.setAccessible(true);
        ArrayList<Combate> combates = (ArrayList<Combate>) combatesField.get(null);

        assertNotNull(combates);
        assertFalse(combates.isEmpty());
    }

    @Test
    public void saveUsersToFileTest() throws IOException,
            ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Initdata.saveUsersToFile();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\steve\\Desktop\\Prueba2\\Practica_MP_Grupo10\\users.dat"))) {
            Field usersField = Initdata.class.getDeclaredField("users");
            usersField.setAccessible(true);

            ArrayList<User> expectedUsers = (ArrayList<User>) usersField.get(null);
            ArrayList<User> actualUsers = (ArrayList<User>) ois.readObject();

            assertNotNull(actualUsers);
            assertFalse(actualUsers.isEmpty());
            assertEquals(expectedUsers.size(), actualUsers.size());

            for (int i = 0; i < expectedUsers.size(); i++) {
                assertEquals(expectedUsers.get(i).getNick(), actualUsers.get(i).getNick(), "Los nicks de los usuarios deberían coincidir");

            }
        }
    }

    @Test
    public void saveInventoriesToFileTest() throws IOException,
            ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Inventory inventoryTest = new Inventory();

        Weapon weapon15 = EquipmentFactory.createWeapon("Longsword2", 3, 3, 2);
        inventoryTest.addWeapon(weapon15);


        Field inventoriesField = Initdata.class.getDeclaredField("inventories");
        inventoriesField.setAccessible(true);
        inventoriesField.set(null, inventoryTest);

        Initdata.saveInventoriesToFile();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\steve\\Desktop\\Prueba2\\Practica_MP_Grupo10\\inventory.dat"))) {
            Inventory actualInventory = (Inventory) ois.readObject();
            assertNotNull(actualInventory);
        }
    }

    @Test
    public void saveCombatesToFileTest() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ArrayList<Combate> combatesTest = new ArrayList<>();
        User user1 = new User("Bot3", "12345", "BotName3", "player");
        User user2 = new User("Bot4", "12345", "BotName4", "player");
        Combate combate = new Combate(user1,user2, 15);
        combatesTest.add(combate);


        Field combatesField = Initdata.class.getDeclaredField("combates");
        combatesField.setAccessible(true);
        combatesField.set(null, combatesTest);

        Initdata.saveCombatesToFile();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\steve\\Desktop\\Prueba2\\Practica_MP_Grupo10\\combates.dat"))) {
            ArrayList<Combate> actualCombates = (ArrayList<Combate>) ois.readObject();
            assertNotNull(actualCombates);
            assertFalse(actualCombates.isEmpty());
            assertEquals(combatesTest.size(), actualCombates.size());

        }
    }
}