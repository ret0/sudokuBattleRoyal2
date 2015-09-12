package ch.elca.students.sudokubattleroyal2.game;

import org.springframework.stereotype.Component;

import ch.elca.students.sudokubattleroyal2.model.GameUpdate;

@Component
public class GameManager {

    private static final int CORRECT_SCORE = 10;
    private static final int WRONG_SCORE = -2;
    private static final int TOO_LATE_SCORE = 0;

    private Game game;

    public Game createAndStart() {
        int[][] solution = SudokuGenerator.createSolution();
        int[][] initialBoardState = SudokuGenerator.createGame(35, solution);
        game = new Game(solution, initialBoardState);
        return game;

    }

    public GameUpdate solve(String playerName, int x, int y, int value) {
        switch (game.solve(x, y, value)) {
        case CORRECT:
            return new GameUpdate(playerName, CORRECT_SCORE, x, y, value, false);
        case WRONG:
            return new GameUpdate(playerName, WRONG_SCORE, x, y, value, false);
        case TOO_LATE:
            return new GameUpdate(playerName, TOO_LATE_SCORE, x, y, value,
                    false);
        case FINISHED:
            return new GameUpdate(playerName, CORRECT_SCORE, x, y, value, true);
        default:
            throw new IllegalArgumentException();
        }
    }
}
