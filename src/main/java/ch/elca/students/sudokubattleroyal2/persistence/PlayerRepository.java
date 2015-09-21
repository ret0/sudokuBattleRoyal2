package ch.elca.students.sudokubattleroyal2.persistence;

import ch.elca.students.sudokubattleroyal2.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that offers the basic persistence related functionality for Player objects.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findOneByPlayerName(String playerName);
}
