package ch.elca.students.sudokubattleroyal2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author rkb
 */
@Entity
public class Player {

    @Id
    private String playerId;

    @Column
    private String playerName;

    @Column
    private int score;

    public Player() {
        // nop
        // required by hibernate
    }

    public Player(String playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
}
