package ch.elca.students.sudokubattleroyal2.game;

import ch.elca.students.sudokubattleroyal2.model.GameUpdate;
import ch.elca.students.sudokubattleroyal2.persistence.PlayerRepository;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameManager {

    @Autowired
    private PlayerRepository playerRepository;

    private static final Integer CORRECT_SCORE = 10;
    private static final Integer WRONG_SCORE = -2;
    private static final Integer TOO_LATE_SCORE = 0;

    private static final Map<Answer, Integer> SCORE_MAPPING = ImmutableMap
            .<Answer, Integer>builder().put(Answer.CORRECT, CORRECT_SCORE)
            .put(Answer.WRONG, WRONG_SCORE)
            .put(Answer.TOO_LATE, TOO_LATE_SCORE)
            .put(Answer.FINISHED, CORRECT_SCORE).build();

    private Game game;

    public Game createAndStart() {
        int[][] solution = SudokuGenerator.createSolution();
        int[][] initialBoardState = SudokuGenerator.createGame(35, solution);
        game = new Game(solution, initialBoardState);
        return game;
    }

    public GameUpdate solve(String playerName, int x, int y, int value) {
        Answer update = game.solve(x, y, value);
        return new GameUpdate(playerName, update, SCORE_MAPPING.get(update)
                .intValue(), x, y, value);
    }

    public boolean isGameStarted() {
        return game != null;
    }

    public void resetGameState() {
        game = null;
        playerRepository.deleteAllInBatch();
    }
}
