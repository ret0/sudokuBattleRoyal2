package ch.elca.students.sudokubattleroyal2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*
         * The "/app" prefix is arbitrary. You can pick any prefix. It’s simply
         * meant to differentiate messages to be routed to message-handling methods to do application work vs
         * messages to be routed to the broker to broadcast to subscribed clients.
         */
        // TODO Set Prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // TODO fix socks endpoint
        registry.addEndpoint("???").withSockJS();
    }

}