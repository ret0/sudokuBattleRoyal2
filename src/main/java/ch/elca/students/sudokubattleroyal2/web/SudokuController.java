package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.model.GameUpdate;
import ch.elca.students.sudokubattleroyal2.model.GameWelcome;
import ch.elca.students.sudokubattleroyal2.model.Player;
import ch.elca.students.sudokubattleroyal2.model.SolveMessage;
import com.google.common.collect.ImmutableList;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SudokuController {

    //@Autowired
    //private PlayerRepository playerRepository;

    /**
     * An attempt at filling in the correct value
     * for one field.
     */
    @MessageMapping("/solve")
    @SendTo("/topic/gameupdate")
    public GameUpdate solve(SolveMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new GameUpdate("player", 2, 0, 0, 9, false);
    }

    @MessageMapping("/join")
    public GameWelcome join(String playerId) throws Exception {
        return new GameWelcome(true);
    }

    @SubscribeMapping("/game.players")
    public List<Player> retrieveParticipants() {
        //return ImmutableList.copyOf(playerRepository.findAll());
        return ImmutableList.of(new Player("id", "name"));
    }


}
