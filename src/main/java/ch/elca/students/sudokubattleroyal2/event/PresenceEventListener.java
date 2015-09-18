package ch.elca.students.sudokubattleroyal2.event;

import ch.elca.students.sudokubattleroyal2.game.PlayerManager;
import ch.elca.students.sudokubattleroyal2.model.Player;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Application Listener that is configured to listen to STOMP Session connect events.
 * <p/>
 * Whenever we receive such an event we register a new player using the PlayerManager.
 */
public class PresenceEventListener implements ApplicationListener<ApplicationEvent> {

    private PlayerManager playerManager;

    public PresenceEventListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();

        // We store the session as we need to be idempotent in the disconnect event processing
        playerManager.add(new Player(headers.getSessionId(), username));
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof SessionConnectEvent) {
            handleSessionConnected((SessionConnectEvent) event);
        }
    }
}
