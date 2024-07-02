package User;

import Character.Character;

public class Player extends User {
    private String record;
    private Character character;

    public Player() {
    }

    public Player(String nick, String name, String record) {
        super(nick, record, name, "player");
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
