package SystemFunction;

import Character.Equipment.Armor;
import Character.Equipment.EquipmentFactory;
import Character.Equipment.Weapon;
import org.junit.jupiter.api.*;
import Database.Initdata;
import User.User;
import User.Player;
//import org.mokito.mokito.*;
import User.RecordPlayer;
import java.util.Random;
import Character.CharacterFactory;
import Character.Character;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

//import static User.RecordPlayer.random;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUpStreams() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    public void testPlayerMenu() {

        Menu.playerMenu();

        String lineSeparator = System.lineSeparator();
        String expectedOutput = "+-------------------------------------------------+" + lineSeparator +
                "| Main Menu                                       |" + lineSeparator +
                "+-------------------------------------------------+" + lineSeparator +
                "| 1. View Character                               |" + lineSeparator +
                "| 2. Delete Character                             |" + lineSeparator +
                "| 3. Modify Active Items                          |" + lineSeparator +
                "| 4. Challenge another user                       |" + lineSeparator +
                "| 5. Message                                      |" + lineSeparator +
                "| 6. Check World Ranking                          |" + lineSeparator +
                "| 7. Exit                                         |" + lineSeparator +
                "+-------------------------------------------------+" + lineSeparator +
                "Choose an option: ";

        String actualOutputNormalized = outContent.toString().replace("\r\n", "\n");
        String expectedOutputNormalized = expectedOutput.replace("\r\n", "\n");

        assertEquals(expectedOutputNormalized, actualOutputNormalized);
    }


    @Test
    public void testAdminMenu() {

        Menu.adminMenu();
        String lineSeparator = System.lineSeparator();
        String expectedOutput = "+-------------------------------------------------+" + lineSeparator +
                "| Admin Menu                                      |" + lineSeparator +
                "+-------------------------------------------------+" + lineSeparator +
                "| 1. Edit Character                               |" + lineSeparator +
                "| 2. Add Items To The Character                   |" + lineSeparator +
                "| 3. Validate Challenges                          |" + lineSeparator +
                "| 4. Block Players                                |" + lineSeparator +
                "| 5. Unlock Players                               |" + lineSeparator +
                "| 6. Exit                                         |" + lineSeparator +
                "+-------------------------------------------------+" + lineSeparator +
                "Choose an option: ";
        String actualNormalized = outContent.toString().replace("\r\n", "\n").replace('\r', '\n');
        String expectedNormalized = expectedOutput.replace("\r\n", "\n").replace('\r', '\n');
        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    public void testCombatListMenu_Empty() {


        Initdata.getCombates().clear();
        Menu.combatListMenu();
        String lineSeparator = System.lineSeparator();

        String expectedOutput =
                        "+-------------------------------------------------+" + lineSeparator +
                        "| Combat List                                     |" + lineSeparator +
                        "+-------------------------------------------------+" + lineSeparator +
                        "No hay combates en este momento." + lineSeparator;
        String actualNormalized = outContent.toString().replace("\r\n", "\n").replace('\r', '\n');
        String expectedNormalized = expectedOutput.replace("\r\n", "\n").replace('\r', '\n');
        assertEquals(expectedNormalized, actualNormalized);
    }
    /*@Test
    public void testCombatListMenu_NotEmpty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Combate> combates = new ArrayList<>();
        User user1 = new User("Bot1","12345","BotName1","player");
        User user2 = new User("Bot2","12345","BotName2","player");
        combates.add(new Combate(user1,user2,100));
        Initdata.getCombates().addAll(combates);

        Menu.combatListMenu();

        String expectedOutput =
                "+-------------------------------------------------+\n" +
                "| Combat List                                     |\n" +
                "+-------------------------------------------------+\n";
                // Aquí iría la representación de los combates en la lista


        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }


    @Test
    void testCombatMenu_Found() {
        Random random = new Random();
        // Configuración de datos de prueba
        User user1 = new User("Bot1", "12345", "BotName1", "player");
        User user2 = new User("Bot2", "12345", "BotName2", "player");
        String record1 = RecordPlayer.generateRecord();
        String record2 = RecordPlayer.generateRecord();

        Player challenger = new Player(user1.getNick(), user1.getName(), record1);
        user1.setPlayer(challenger);

        Player challenged = new Player(user2.getNick(), user2.getName(), record2);
        user2.setPlayer(challenged);

        Weapon botWeapon1 = EquipmentFactory.createWeapon("botweapon", 2, 2, 2);
        Armor botArmor1 = EquipmentFactory.createArmor("botArmor", 1, 2);
        Character character1 = CharacterFactory.createCharacter("vampire", "Dracula_1", random.nextInt(200), 150,
                random.nextInt(20), 0, random.nextInt(400), 0);
        character1.setArmor(botArmor1);
        character1.setWeapon(botWeapon1);
        character1.setType("vampire");
        user1.getPlayer().setCharacter(character1);
        character1.setGold(200);

        Weapon botWeapon2 = EquipmentFactory.createWeapon("botweapon", 2, 2, 2);
        Armor botArmor2 = EquipmentFactory.createArmor("botArmor", 1, 2);
        Character character2 = CharacterFactory.createCharacter("vampire", "Dracula_2", random.nextInt(200), 150,
                random.nextInt(20), 0, random.nextInt(400), 0);
        character2.setArmor(botArmor2);
        character2.setWeapon(botWeapon2);
        character2.setType("vampire");
        user2.getPlayer().setCharacter(character2);
        character2.setGold(200);
        ArrayList<Combate> combates = new ArrayList<>();
        combates.add(new Combate(challenger, challenged, 100));

        Combate result = Menu.combatMenu("Bot1");

        String expectedOutput =
                "+-------------------------------------------------+\n" +
                "| Combat found involving Bot1:                    |\n" +
                "+-------------------------------------------------+\n" +
                "Challenger: \n" +
                "Nick: " + challenger.getNick() + "\n" +
                "Name: " + challenger.getName() + "\n" +
                "Character Type: " + challenger.getPlayer().getCharacter().getType() + "\n" +
                "Health: " + challenger.getPlayer().getCharacter().getHealth() + "\n" +
                "Power: " + challenger.getPlayer().getCharacter().getPower() + "\n" +
                "Gold: " + challenger.getPlayer().getCharacter().getGold() + "\n" +
                "Armor: " + challenger.getPlayer().getCharacter().getArmor().getName() + "\n" +
                "Weapon: " + challenger.getPlayer().getCharacter().getWeapon().getName() + "\n" +
                "Challenged: \n" +
                "Nick: " + challenged.getNick() + "\n" +
                "Name: " + challenged.getName() + "\n" +
                "Character Type: " + challenged.getPlayer().getCharacter().getType() + "\n" +
                "Health: " + challenged.getPlayer().getCharacter().getHealth() + "\n" +
                "Power: " + challenged.getPlayer().getCharacter().getPower() + "\n" +
                "Gold: " + challenged.getPlayer().getCharacter().getGold() + "\n" +
                "Armor: " + challenged.getPlayer().getCharacter().getArmor().getName() + "\n" +
                "Weapon: " + challenged.getPlayer().getCharacter().getWeapon().getName() + "\n" +
                "+-------------------------------------------------+\n" +
                "| Stake: 100\n" +
                "+-------------------------------------------------+\n";
        String actualNormalized = outContent.toString().replace("\r\n", "\n").replace('\r', '\n');
        String expectedNormalized = expectedOutput.replace("\r\n", "\n").replace('\r', '\n');
        assertEquals(expectedNormalized, actualNormalized);

        assertNotNull(result);
    }*/

    /*@Test
    void testCombatMenu_NotFound() {
        // Configuración de datos de prueba sin combates
        Initdata.getCombates().clear();

        // Ejecutar el método a probar
        Combate result = Menu.combatMenu("UsuarioInexistente");

        // Verificar la salida esperada
        String expectedOutput = "No combats found for UsuarioInexistente\n";
        assertEquals(expectedOutput, outContent.toString());

        // Verificar que se devuelva null
        assertNull(result);
    }*/


    @Test
        void authMenu () {
        }

        @Test
        void chooseCharacterMenu () {
        }

        @Test
        void chooseMinionMenu () {
        }

        @Test
        void adminOption1 () {
        }

        @Test
        void userMenu () {
        }

        @Test
        void rankingMenu () {
        }

        @Test
        void inventoryMenu () {
        }
    }