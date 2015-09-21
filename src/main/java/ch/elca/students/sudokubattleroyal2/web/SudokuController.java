package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.model.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SudokuController {

    /**
     * An attempt at filling in the correct value
     * for one field.
     */
    @MessageMapping("/solve")
    public void solve(String message) throws Exception {
        // TODO implement "solve()"
    }

    /**
     * Sends the message back to (only) the user that just subscribed to this topic
     *
     * @return a list of already connected players
     */
    public List<Player> retrievePlayers() {
        // TODO FIXME
        return null;
    }

}
