package ch.elca.students.sudokubattleroyal2.event;

import ch.elca.students.sudokubattleroyal2.game.PlayerManager;
import ch.elca.students.sudokubattleroyal2.model.Player;
import ch.elca.students.sudokubattleroyal2.model.PlayerConnectedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;

public class PresenceEventListener implements ApplicationListener<ApplicationEvent> {

    private PlayerManager playerManager;

    private SimpMessagingTemplate messagingTemplate;

    public PresenceEventListener(SimpMessagingTemplate messagingTemplate, PlayerManager playerManager) {
        this.messagingTemplate = messagingTemplate;
        this.playerManager = playerManager;
    }

    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();

        if (playerManager.playerIsNew(username)) {
            messagingTemplate.convertAndSend("/topic/game/login", new PlayerConnectedEvent(username));
            playerManager.add(new Player(headers.getSessionId(), username));
        }
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof SessionConnectEvent) {
            handleSessionConnected((SessionConnectEvent) event);
        }
    }
}
