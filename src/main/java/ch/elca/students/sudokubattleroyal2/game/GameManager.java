package ch.elca.students.sudokubattleroyal2.game;

import ch.elca.students.sudokubattleroyal2.model.GameUpdate;
import org.springframework.stereotype.Component;

@Component
public class GameManager {

    private Game game;

    public void createAndStart() {
        int[][] solution = SudokuGenerator.createSolution();
        int[][] initialBoardState = SudokuGenerator.createGame(35,solution);
        game = new Game(solution, initialBoardState);

    }

    public GameUpdate solve(String playerName, int x, int y, int value) {
        switch (game.solve(x, y, value)) {
            case CORRECT:
            case WRONG:
            case TOO_LATE:
            case FINISHED:

        }
        return new GameUpdate(playerName, 2, 0,0,7,false);
    }
}
