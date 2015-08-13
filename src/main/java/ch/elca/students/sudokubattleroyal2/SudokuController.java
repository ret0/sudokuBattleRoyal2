package ch.elca.students.sudokubattleroyal2;

import ch.elca.students.sudokubattleroyal2.model.GameUpdate;
import ch.elca.students.sudokubattleroyal2.model.GameWelcome;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SudokuController {

    /**
     * An attempt at filling in the correct value
     * for one field.
     */
    @MessageMapping("/solve")
    @SendTo("/topic/gameupdate")
    public GameUpdate solve(SolveMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new GameUpdate("player", 2, 0,0,9,false);
    }

    @MessageMapping("/join")
    public GameWelcome join(String playerId) throws Exception {
        return new GameWelcome(true);
    }











}
