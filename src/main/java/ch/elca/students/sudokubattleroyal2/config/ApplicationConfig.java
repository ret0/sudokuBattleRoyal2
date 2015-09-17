package ch.elca.students.sudokubattleroyal2.config;

import ch.elca.students.sudokubattleroyal2.game.PlayerManager;
import ch.elca.students.sudokubattleroyal2.event.PresenceEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author rkb
 */
@Configuration
public class ApplicationConfig {

    public static class Destinations {
        private Destinations() {
        }

        private static final String LOGIN = "/topic/game/login";
    }

    @Bean
    @Description("Tracks user presence (join / leave) and broadcasts it to all connected users")
    public PresenceEventListener presenceEventListener(SimpMessagingTemplate messagingTemplate, PlayerManager playerManager) {
        PresenceEventListener presence = new PresenceEventListener(messagingTemplate, playerManager);
        presence.setLoginDestination(Destinations.LOGIN);
        return presence;
    }
}
