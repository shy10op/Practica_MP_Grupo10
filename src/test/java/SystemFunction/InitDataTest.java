/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SystemFunction;

import Character.Equipment.Inventory;
import Database.Initdata;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yshdo
 */
public class InitDataTest {

    @Test
    public void testGenerateInitialInventories() {
        Initdata.generateInitialInventories();
        assertFalse(Inventory.getWeapons().isEmpty() && Inventory.getArmors().isEmpty());
    }
    
    
}
