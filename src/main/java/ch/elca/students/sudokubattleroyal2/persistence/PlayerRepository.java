package ch.elca.students.sudokubattleroyal2.persistence;

import ch.elca.students.sudokubattleroyal2.model.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * @author rkb
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
