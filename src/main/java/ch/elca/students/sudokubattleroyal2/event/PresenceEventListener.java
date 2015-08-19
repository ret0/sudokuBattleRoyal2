package ch.elca.students.sudokubattleroyal2.event;

import ch.elca.students.sudokubattleroyal2.model.Player;
import ch.elca.students.sudokubattleroyal2.model.PlayerConnectedEvent;
import ch.elca.students.sudokubattleroyal2.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;


public class PresenceEventListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private PlayerRepository playerRepository;

    private SimpMessagingTemplate messagingTemplate;

    private String loginDestination;

    private String logoutDestination;

    public PresenceEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();

        PlayerConnectedEvent playerConnectedEvent = new PlayerConnectedEvent(username);
        messagingTemplate.convertAndSend(loginDestination, playerConnectedEvent);

        // We store the session as we need to be idempotent in the disconnect event processing
        playerRepository.save(new Player(headers.getSessionId(), username));
    }


    public void setLoginDestination(String loginDestination) {
        this.loginDestination = loginDestination;
    }

    public void setLogoutDestination(String logoutDestination) {
        this.logoutDestination = logoutDestination;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof SessionConnectEvent) {
            handleSessionConnected((SessionConnectEvent) event);
        }
    }
}
