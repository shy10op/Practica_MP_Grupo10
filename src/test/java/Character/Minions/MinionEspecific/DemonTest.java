package Character.Minions.MinionEspecific;

import Character.Minions.Minion;
import Character.Minions.MinionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemonTest {
    Minion minion = MinionFactory.createMinion("human", "John", 100, Human.Loyalty.HIGH, null, null, 0);

    @Test
    void getPactDescription() {
    }

    @Test
    void setPactDescription() {
    }

    @Test
    void isPact() {
    }

    @Test
    void setPact() {
    }
}