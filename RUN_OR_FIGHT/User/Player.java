package User;

import java.io.Serializable;

public class Player extends User implements Serializable{
    private String record; 

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

}
