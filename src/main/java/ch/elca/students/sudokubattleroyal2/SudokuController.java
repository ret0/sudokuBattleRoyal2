package ch.elca.students.sudokubattleroyal2;

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
    @SendTo("/topic/game/board")
    public Board solve(SolveMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Board("Hello, " + message.getName() + "!");
    }


}
