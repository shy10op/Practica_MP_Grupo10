package User;

import java.io.Serializable;
import Character.Character;

public class Player extends User implements Serializable {
    private String record;
    private Character character;

    public Player(String nick, String password, String name, String record) {
        super(nick, password, name);
        this.record = record;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

}
