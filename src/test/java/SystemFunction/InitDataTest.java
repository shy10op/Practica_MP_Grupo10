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
        cleanTestFiles();
        combatList = Initdata.getCombateList();
        userList = Initdata.getUsers();
        weapons = Inventory.getWeapons();
        armors = Inventory.getArmors();

    }

    @AfterEach
    public void tearDown() {
        // Limpiar los archivos de prueba después de cada prueba
        cleanTestFiles();
    }

    @Test
    public void testGenerateInitialInventories() {
        Initdata.generateInitialInventories();
        assertFalse(Inventory.getWeapons().isEmpty() && Inventory.getArmors().isEmpty());
    }

    private void cleanTestFiles() {
        List<String> testFiles = Arrays.asList(USER, INVENTORY, COMBATES);
        testFiles.forEach(file -> {
            File testFile = new File(file);
            if (testFile.exists()) {
                testFile.delete();
            }
        });
    }

    @Test
    public void testCheckUsersFile() {
        Initdata.checkUsersFile();
        File usersFile = new File(InitdataTest.USER);
        assertTrue(usersFile.exists());
    }

    @Test
    public void testGenerateRandomCombat() {
        int combatSize = userList.size() / 2;
        Initdata.generateRandomCombates();
        assertTrue(combatList.size() == combatSize);
    }

    @Test
    public void testGenerateRandomCombatNullUserFile() { //cuando el userFile es vacio
        userList.clear();
        Initdata.generateRandomCombates();
        assertTrue(combatList.isEmpty());
    }

    @Test
    public void testgenerateInitialInventories() {
        armors.clear();
        weapons.clear();
        Initdata.generateInitialInventories();
        assertTrue(!armors.isEmpty() && !weapons.isEmpty());
    }
    
    
    

}
