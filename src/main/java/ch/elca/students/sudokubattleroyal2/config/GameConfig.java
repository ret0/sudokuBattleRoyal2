package ch.elca.students.sudokubattleroyal2.config;

import ch.elca.students.sudokubattleroyal2.event.PresenceEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author rkb
 */
@Configuration
@EnableJpaRepositories("ch.elca.students.sudokubattleroyal2.persistence")
public class GameConfig {

    public static class Destinations {
        private Destinations() {
        }

        private static final String LOGIN = "/topic/game.login";
        //private static final String LOGOUT = "/topic/chat.logout";
    }

    @Bean
    @Description("Tracks user presence (join / leave) and broacasts it to all connected users")
    public PresenceEventListener presenceEventListener(SimpMessagingTemplate messagingTemplate) {
        PresenceEventListener presence = new PresenceEventListener(messagingTemplate);
        presence.setLoginDestination(Destinations.LOGIN);
        return presence;
    }
}
