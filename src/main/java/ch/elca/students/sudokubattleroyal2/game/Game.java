package ch.elca.students.sudokubattleroyal2.game;

public class Game {

    private int[][] solution;
    private int[][] game;

    public Game(int[][] solution, int[][] game) {
        this.solution = solution;
        this.game = game;
    }

    public int[][] getGameBoard() {
        return this.game;
    }

    public GameUpdateType solve(int x, int y, int value) {
        if (solution[x][y] == value && game[x][y] == 0) {
            game[x][y] = value;
            if (isFinished()) {
                return GameUpdateType.FINISHED;
            }
            return GameUpdateType.CORRECT;
        } else if (solution[x][y] == value && game[x][y] != 0) {
            return GameUpdateType.TOO_LATE;
        }
        return GameUpdateType.WRONG;
    }

    private boolean isFinished() {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[0].length; j++) {
                if (game[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
