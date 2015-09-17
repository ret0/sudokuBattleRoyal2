package ch.elca.students.sudokubattleroyal2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.elca.students.sudokubattleroyal2.game.Game;
import ch.elca.students.sudokubattleroyal2.game.GameManager;

@RestController
public class GameStartController {

    private static final Logger log = LoggerFactory
            .getLogger(GameStartController.class);

    @Autowired
    private GameManager gameManager;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping("/admin/start")
    public void createAndStartGame() {
        Game game = gameManager.createAndStart();
        simpMessagingTemplate.convertAndSend("/topic/game/start",
                game.getGameBoard());
        log.info("Game Started...");
    }

    @RequestMapping("/admin/reset")
    public void resetGame() {
        gameManager.resetGameState();
        log.info("Game Reset...");
    }
}
