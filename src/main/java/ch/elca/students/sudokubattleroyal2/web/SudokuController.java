package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.persistence.PlayerRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class SudokuController {

    /**
     * An attempt at filling in the correct value
     * for one field.
     */
    @MessageMapping("/solve")
    public void solve(String message) throws Exception {
//        GameUpdate gameUpdate = gameManager.solve(principal.getName(), message.getX(), message.getY(), message.getValue());
//        simpMessagingTemplate.convertAndSend("/topic/game/update", gameUpdate);
    }
}
