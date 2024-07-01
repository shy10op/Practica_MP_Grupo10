/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SystemFunction;

import Character.Equipment.Armor;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import Database.Initdata;
import User.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yshdo
 */
public class InitdataTest {

    private static final String USER
            = "src\\main\\java\\Database\\DatabaseFile\\users.dat";
    private static final String INVENTORY
            = "src\\main\\java\\Database\\DatabaseFile\\inventory.dat";
    private static final String COMBATES
            = "src\\main\\java\\Database\\DatabaseFile\\combates.dat";
    private static ArrayList<Combate> combatList;
    private static ArrayList<User> userList;
    private static ArrayList<Weapon> weapons;
    private static ArrayList<Armor> armors;

    @BeforeEach
    public void setUp() {
        // Limpiar los archivos de prueba antes de cada prueba
        Initdata.startInitData();
        combatList = Initdata.getCombateList();
        userList = Initdata.getUsers();
        weapons = Inventory.getWeapons();
        armors = Inventory.getArmors();
    }

    @Test
    public void testGenerateRandomCombat() {
        combatList.clear();//limpiar la lista de combate
        int combatSize = userList.size() / 2;// el tamaño de la lista de combate tiene que ser la mitade del tamaño de los Bots
        Initdata.generateRandomCombates();//generar combates 
        assertTrue(combatList.size() == combatSize);//Comprobar el tamaño
    }

    @Test
    public void testGenerateRandomCombatNullUserFile() { //cuando el userFile es vacio
        userList.clear();//Limpiar la lista de usuarios
        Initdata.generateRandomCombates();//generar combates con la lista de usuario vacia
        assertTrue(combatList.isEmpty());//se comrpruba que no se ha generado ningun combates
    }

    @Test
    public void testgenerateInitialInventories() {
        weapons.clear();//limpiar la lista de armas
        armors.clear();//limpiar la lista de armaduras
        Initdata.generateInitialInventories();//genera inventarios
        assertTrue(!armors.isEmpty() && !weapons.isEmpty());//comprobar que no estan vacias las dos listas 
    }

    @Test
    public void testGenerateBots() {
        Initdata.generateBots();
        assertEquals(11, userList.size());//10 jugadores y 1 admin
    }

}
