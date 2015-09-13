package ch.elca.students.sudokubattleroyal2.model;

import ch.elca.students.sudokubattleroyal2.game.Answer;

/**
 * Created by mkf on 13.08.2015.
 */
public class GameUpdate {

    private String playerId;
    private Answer update;
    private int scoreDelta;
    private int x;
    private int y;
    private int value;

    public GameUpdate() {
    }

    public GameUpdate(String playerId, Answer update, int scoreDelta, int x,
            int y, int value) {
        this.playerId = playerId;
        this.update = update;
        this.scoreDelta = scoreDelta;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the update
     */
    public Answer getUpdate() {
        return update;
    }

    /**
     * @param update
     *            the update to set
     */
    public void setUpdate(Answer update) {
        this.update = update;
    }

    public int getScoreDelta() {
        return scoreDelta;
    }

    public void setScoreDelta(int scoreDelta) {
        this.scoreDelta = scoreDelta;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
