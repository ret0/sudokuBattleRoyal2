package ch.elca.students.sudokubattleroyal2.persistence;

import ch.elca.students.sudokubattleroyal2.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that offers CRUD operations for Player Entities
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    /**
     * @return a single Player object with that name, if there exists such a player, null otherwise.
     */
    Player findOneByPlayerName(String playerName);
}
