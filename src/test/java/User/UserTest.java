package User;

import static org.junit.jupiter.api.Assertions.*;

import Database.Initdata;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {//done

    private ArrayList<User> users;

    @BeforeEach
    public void setup() throws Exception {
        Initdata.startInitData();//cargar base de datos
        users = Initdata.getUsers();//obtener la lista de usuarios
    }

    @Test
    public void testFindUserExists() {
        String userNick = "Bot1";
        User resultUser = User.findUser(userNick);

        if (resultUser == null) {
            fail("User not Exist");
        }

        assertNotNull(resultUser);//no es nulo
    }

    @Test
    public void testDeleteUser() {
        User resultUser = User.findUser("Bot1");

        int size = users.size();//el tamaño antes de borrar
        if (resultUser == null) {
            fail("User not exist");
        } else {
            User.deleteUser(resultUser.getNick());//dato de entrada el nick de usuario
            int newSize = users.size();//tamaño despues de borrar
            assertTrue(size > newSize);//comprobar ambos tamaños
        }
    }

}
