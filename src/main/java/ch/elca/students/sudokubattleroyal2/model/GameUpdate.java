package ch.elca.students.sudokubattleroyal2.model;

import ch.elca.students.sudokubattleroyal2.game.GameUpdateType;

/**
 * Created by mkf on 13.08.2015.
 */
public class GameUpdate {

    private String playerName;
    private GameUpdateType type;
    private int scoreDelta;
    private int x;
    private int y;
    private int value;

    public GameUpdate(String playerName, GameUpdateType type, int scoreDelta, int x,
                      int y, int value) {
        this.playerName = playerName;
        this.type = type;
        this.scoreDelta = scoreDelta;
        this.x = x;
        this.y = y;
        if (GameUpdateType.WRONG.equals(type)) {
            this.value = 0;
        } else {
            this.value = value;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the type
     */
    public GameUpdateType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(GameUpdateType type) {
        this.type = type;
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
