package SystemFunction;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Database.Initdata;
import User.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private static ByteArrayOutputStream outContent;
    private static PrintStream originalOut;

    @BeforeAll
    public static void setUpStreams() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    public void testPlayerMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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

        System.setOut(System.out);
    }


    @Test
    public void testAdminMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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

        System.setOut(System.out);
    }

    @Test
    public void testCombatListMenu_Empty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
    }*/


        @Test
        void combatMenu () {
        }

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