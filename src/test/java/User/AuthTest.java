package User;

import Database.Initdata;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AuthTest {

    @BeforeEach
    public void setup() {
        Initdata.startInitData();
        ArrayList<User> users = Initdata.getUsers();
        User userTest = new User("testBot", "12345", "testBot", "player");
        users.add(userTest);
        Initdata.saveUsersToFile();
    }

    @Test
    public void loginTest() {
        String nick = "testBot";
        String password = "12345";
        User user = Auth.loginAuth(nick, password);
        assertNotNull(user);
    }

    @Test
    public void signUpTest() {
        String nick = "BotTest";
        String password = "12345";
        String name = "BotTest";
        String role = "player";
        User newUser = new User(nick, password, name, role);
        Auth.singUp(newUser);
        
        User user = User.findUser(nick);
        assertNotNull(user);
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
