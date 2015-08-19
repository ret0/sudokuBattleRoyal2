package ch.elca.students.sudokubattleroyal2.model;

/**
 * @author rkb
 */
public class PlayerConnectedEvent {

    private String username;

    public PlayerConnectedEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
