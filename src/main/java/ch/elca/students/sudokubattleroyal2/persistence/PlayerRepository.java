package ch.elca.students.sudokubattleroyal2.persistence;

import ch.elca.students.sudokubattleroyal2.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rkb
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findOneByPlayerName(String playerName);
}
