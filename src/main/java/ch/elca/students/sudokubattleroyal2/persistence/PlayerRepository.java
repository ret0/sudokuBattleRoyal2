package ch.elca.students.sudokubattleroyal2.persistence;

import ch.elca.students.sudokubattleroyal2.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author rkb
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByPlayerName(String playerName);
}
