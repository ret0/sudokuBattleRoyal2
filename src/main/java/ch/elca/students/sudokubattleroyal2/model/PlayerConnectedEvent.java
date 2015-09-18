package ch.elca.students.sudokubattleroyal2.model;

/**
 * @author rkb
 */
public class PlayerConnectedEvent {

    private String playerName;

    public PlayerConnectedEvent(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}