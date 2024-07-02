package User;

import Character.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlayerTest {

    private Player player;
    private Character character;

    @BeforeEach
    public void setUp() {
        player = new Player("playerNick", "playerName", "record");
        character = new Character(); // Supuesto constructor sin parámetros
    }

    @Test
    public void testDefaultConstructor() {
        Player defaultPlayer = new Player();
        assertNull(defaultPlayer.getNick());
        assertNull(defaultPlayer.getName());
        assertNull(defaultPlayer.getRecord());
        assertNull(defaultPlayer.getCharacter());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("playerNick", player.getNick());
        assertEquals("playerName", player.getName());
        assertEquals("record", player.getRecord());
        assertNull(player.getCharacter());
    }

    @Test
    public void testGetRecord() {
        assertEquals("record", player.getRecord());
    }

    @Test
    public void testSetRecord() {
        player.setRecord("newRecord");
        assertEquals("newRecord", player.getRecord());
    }

    @Test
    public void testGetCharacter() {
        assertNull(player.getCharacter());
    }

    @Test
    public void testSetCharacter() {
        player.setCharacter(character);
        assertEquals(character, player.getCharacter());
    }
}
