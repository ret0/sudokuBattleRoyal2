package ch.elca.students.sudokubattleroyal2.game;

import ch.elca.students.sudokubattleroyal2.model.Player;
import ch.elca.students.sudokubattleroyal2.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PlayerManager {

    @Autowired
    private PlayerRepository playerRepository;

    public void add(Player player) {
        playerRepository.save(player);
    }

    public boolean playerIsNew(String playerName) {
        return playerRepository.findOneByPlayerName(playerName) == null;
    }

    /**
     * @return all Player objects
     */
    public List<Player> findAll() {
        return playerRepository.findAll();
    }
}
