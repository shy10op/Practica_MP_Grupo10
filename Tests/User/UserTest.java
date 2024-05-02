package User;

import Database.Initdata;
import SystemFunction.Combate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    public void setup() throws Exception {
        Initdata.startInitData();

        Field field = Initdata.class.getDeclaredField("FILENAME");
        field.setAccessible(true);
        String filename = (String) field.get(null);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Initdata.saveUsersToFile();
    }

    @Test
    public void testFindUserExists() {
        String nickExistente = "Bot7";
        User result = User.findUser(nickExistente);
        assertNotNull(result);
        assertEquals(nickExistente.toLowerCase(), result.getNick().toLowerCase());
    }

    @Test
    public void testFindUserNotExists() {
        String nickNoExistente = "nickNoExistente";
        User result = User.findUser(nickNoExistente);
        assertNull(result);
    }

    @Test
    public void testDeleteUser() {
        String nickParaEliminar = "Bot7";
        User userToRemove = User.findUser(nickParaEliminar);
        assertNotNull(userToRemove);

        User.deleteUser(nickParaEliminar);

        User userAfterDeletion = User.findUser(nickParaEliminar);
        assertNull(userAfterDeletion);

        ArrayList<User> users = Initdata.getUsers();
        for (User user : users) {
            assertNotEquals(nickParaEliminar.toLowerCase(), user.getNick().toLowerCase());
        }
    }

}