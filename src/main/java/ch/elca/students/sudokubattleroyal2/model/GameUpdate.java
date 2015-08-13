package ch.elca.students.sudokubattleroyal2.model;

/**
 * Created by mkf on 13.08.2015.
 */
public class GameUpdate {

    private final String playerId;
    private final int scoreDelta;
    private final int x;
    private final int y;
    private final int value;
    private final boolean gameOver;

    public GameUpdate(String playerId, int scoreDelta, int x, int y, int value, boolean gameOver) {
        this.playerId = playerId;
        this.scoreDelta = scoreDelta;
        this.x = x;
        this.y = y;
        this.value = value;
        this.gameOver = gameOver;
    }
}
