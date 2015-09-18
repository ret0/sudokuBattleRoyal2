package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.model.Player;
import ch.elca.students.sudokubattleroyal2.persistence.PlayerRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SudokuController {

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Sends the message back to (only) the user that just subscribed to this topic
     *
     * @return a list of already connected players
     */
    @SubscribeMapping("/game/players")
    public List<Player> retrievePlayers() {
        return ImmutableList.copyOf(playerRepository.findAll());
    }

}
