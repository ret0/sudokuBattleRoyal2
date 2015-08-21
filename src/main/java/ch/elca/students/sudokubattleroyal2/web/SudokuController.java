package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.game.GameManager;
import ch.elca.students.sudokubattleroyal2.model.GameUpdate;
import ch.elca.students.sudokubattleroyal2.model.Player;
import ch.elca.students.sudokubattleroyal2.model.SolveMessage;
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

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameManager gameManager;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * An attempt at filling in the correct value
     * for one field.
     */
    @MessageMapping("/solve")
    public void solve(SolveMessage message, Principal principal) throws Exception {
        GameUpdate gameUpdate = gameManager.solve(principal.getName(), message.getX(), message.getY(), message.getValue());
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/attempt", gameUpdate);
        simpMessagingTemplate.convertAndSend("/topic/game/update", gameUpdate);
    }

    @SubscribeMapping("/game/players")
    public List<Player> retrieveParticipants() {
        return ImmutableList.copyOf(playerRepository.findAll());
    }

}
