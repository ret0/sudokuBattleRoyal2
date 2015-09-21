package ch.elca.students.sudokubattleroyal2.model;

/**
 * A solve-attempt sent by a client, x and y are the coordinates
 * <p/>
 * (0/0) is top left
 */
public class SolveMessage {

    private int x;
    private int y;
    private int value;

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
