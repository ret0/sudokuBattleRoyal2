package ch.elca.students.sudokubattleroyal2.model;

/**
 * Created by mkf on 13.08.2015.
 */
public class GameUpdate {

    private String playerId;
    private int scoreDelta;
    private int x;
    private int y;
    private int value;
    private boolean gameOver;

    public GameUpdate() {
    }

    public GameUpdate(String playerId, int scoreDelta, int x, int y, int value, boolean gameOver) {
        this.playerId = playerId;
        this.scoreDelta = scoreDelta;
        this.x = x;
        this.y = y;
        this.value = value;
        this.gameOver = gameOver;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
