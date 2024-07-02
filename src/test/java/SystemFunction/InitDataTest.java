/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SystemFunction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Character.Equipment.Armor;
import Character.Equipment.Inventory;
import Character.Equipment.Weapon;
import Database.Initdata;
import User.User;

/**
 *
 * @author yshdo
 */
public class InitDataTest {

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
        combatList.clear();
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
