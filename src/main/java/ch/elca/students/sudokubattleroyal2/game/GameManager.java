package ch.elca.students.sudokubattleroyal2.game;

import ch.elca.students.sudokubattleroyal2.model.GameUpdate;
import org.springframework.stereotype.Component;

@Component
public class GameManager {

    public synchronized GameUpdate solve(String playerName, int x, int y, int value) {
        return new GameUpdate(playerName, GameUpdateType.CORRECT, 10, x, y, value);
    }

}
