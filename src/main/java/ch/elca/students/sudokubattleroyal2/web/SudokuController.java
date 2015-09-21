package ch.elca.students.sudokubattleroyal2.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

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
}
