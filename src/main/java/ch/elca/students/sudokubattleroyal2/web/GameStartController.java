package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.game.Game;
import ch.elca.students.sudokubattleroyal2.game.GameManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameStartController implements EnvironmentAware {

    private static final Logger log = LoggerFactory
            .getLogger(GameStartController.class);

    private static final String ENV_KEY_APPLICATION_ID = "vcap.application.application_id";

    @Autowired
    private GameManager gameManager;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private String applicationIdFromEnv;

    @RequestMapping(value = "/admin/start", method = RequestMethod.POST)
    public void createAndStartGame(@RequestParam("appId") String appId) {
        if (appId.equals(applicationIdFromEnv)) {
            Game game = gameManager.createAndStart();
            simpMessagingTemplate.convertAndSend("/topic/game/start",
                    game.getGameBoard());
            log.info("Game Started...");
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        String environmentProperty = environment.getProperty(ENV_KEY_APPLICATION_ID);
        if (environmentProperty != null && environmentProperty.length() > 0) {
            // we assume that we are running in a CF environment and use the env variable
            applicationIdFromEnv = environmentProperty;
        } else {
            // we assume that we run in a dev environment and fall back to a hardcoded value
            applicationIdFromEnv = "battle";
        }

    }
}
