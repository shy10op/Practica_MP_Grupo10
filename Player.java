package User;

import java.io.Serializable;
import Character.Character;

public class Player extends User implements Serializable {
    private String record;
    private Character character;

    public Player(String nick, String password, String name, String record) {
        super(nick, password, name, "no");
        this.record = RecordPlayer.generateRecord();
    }

    @Override
    public void mainMenu() {
        System.out.println("   Main menu");
        System.out.println("1. Register Character");
        System.out.println("2. Unenroll Character");
        System.out.println("3. Modify Active Items");
        System.out.println("4. Challenge another user");
        System.out.println("5. Check History");
        System.out.println("6. Check World Ranking");
        System.out.println("7. Exit");

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
