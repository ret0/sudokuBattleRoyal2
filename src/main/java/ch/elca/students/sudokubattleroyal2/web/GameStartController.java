package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.game.GameManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameStartController {

    private static final Logger log = LoggerFactory.getLogger(GameStartController.class);

    @Autowired
    private GameManager gameManager;

    @RequestMapping("/admin/start")
    public void createAndStartGame() {
        gameManager.createAndStart();
        log.info("Game Started...");
    }
}
