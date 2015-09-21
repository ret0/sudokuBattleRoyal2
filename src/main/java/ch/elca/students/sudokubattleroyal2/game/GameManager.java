package ch.elca.students.sudokubattleroyal2.game;

import ch.elca.students.sudokubattleroyal2.model.GameUpdate;
import ch.elca.students.sudokubattleroyal2.model.Player;
import ch.elca.students.sudokubattleroyal2.persistence.PlayerRepository;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GameManager {

    @Autowired
    private PlayerRepository playerRepository;

    private static final Logger log = LoggerFactory
            .getLogger(GameManager.class);

    private static final Integer CORRECT_SCORE = 6;
    private static final Integer WRONG_SCORE = -2;
    private static final Integer TOO_LATE_SCORE = 0;

    private static final int NUMBER_OF_BLANK_FIELDS = 42;

    private static final Map<GameUpdateType, Integer> SCORE_MAPPING = ImmutableMap
            .<GameUpdateType, Integer>builder().put(GameUpdateType.CORRECT, CORRECT_SCORE)
            .put(GameUpdateType.WRONG, WRONG_SCORE)
            .put(GameUpdateType.TOO_LATE, TOO_LATE_SCORE)
            .put(GameUpdateType.FINISHED, CORRECT_SCORE).build();

    private Game game;

    public Game createAndStart() {
        int[][] solution = SudokuGenerator.createSolution();
        int[][] initialBoardState = SudokuGenerator.createGame(NUMBER_OF_BLANK_FIELDS, solution);
        game = new Game(solution, initialBoardState);
        return game;
    }

    public synchronized GameUpdate solve(String playerName, int x, int y, int value) {
        GameUpdateType solveResponse = game.solve(x, y, value);
        GameUpdate gameUpdate = new GameUpdate(playerName, solveResponse, SCORE_MAPPING.get(solveResponse)
                .intValue(), x, y, value);
        updatePlayerScore(gameUpdate);
        logCurrentPlayerScore();
        return gameUpdate;
    }

    private void logCurrentPlayerScore() {
        String currentScoreBoard = playerRepository.findAll().stream().
                sorted((p1, p2) -> Integer.valueOf(p1.getScore()).compareTo(p2.getScore())).
                map(player -> "Playername: " + player.getPlayerName() + " Score: " + player.getScore()).
                collect(Collectors.joining("\n"));

        log.info("SCOREBOARD:\n" + currentScoreBoard);
    }

    private void updatePlayerScore(GameUpdate gameUpdate) {
        Player playerEntity = playerRepository.findOneByPlayerName(gameUpdate.getPlayerName());
        playerEntity.setScore(playerEntity.getScore() + gameUpdate.getScoreDelta());
        playerRepository.save(playerEntity);
    }

    public boolean isGameStarted() {
        return game != null;
    }

}
