package User;

import Database.Initdata;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class AuthTest {

    @BeforeAll
    public static void setup() {
        Initdata.startInitData();
        ArrayList<User> users = Initdata.getUsers();
        User userTest = new User("testBot", "12345678", "testBot", "player");
        users.add(userTest);
        Initdata.saveUsersToFile();
    }

    @Test
    public void loginTest() {
        String nick = "Bot2";
        String password = "wewewewe";
        User user = Auth.loginAuth(nick, password);
        assertNotNull(user);
    }

    @Test
    public void signUpTest() {
        String nick = "testBot";
        String password = "12345678";
        String name = "BotTest";
        String role = "player";
        User newUser = new User(nick, password, name, role);
        Auth.singUp(newUser);

        User userLog = Auth.loginAuth(nick, password);

        assertNotNull(userLog);
    }

    @Test
    public void generateRecordTest() {
        final int LENGTH = 5;
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String record = Auth.generateRecord();

        assertNotNull("El registro no debe ser nulo", record);
        assertEquals(LENGTH, record.length());

        // Afirmar que todos los caracteres son o letras o digitos del ALPHABET
        for (int i = 0; i < LENGTH; i++) {
            assertTrue(
                    ALPHABET.indexOf(record.charAt(i)) != -1);
        }
    }
}
