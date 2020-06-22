package com.dxp.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

/**
 * websocket配置类
 *
 * @author carzy
 * @date 2020/6/22
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册节点, SockJS协议
     * http://localhost:8080/webSocket 连接服务器的WebSocket
     * withSockJS()表示支持socktJS访问，在浏览器中使用
     *
     * @param registry 注册STOMP协议的节点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/webSocket").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 这里的消息代理是/topic
     *
     * @param config 消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration webSocketTransportRegistration) {
        webSocketTransportRegistration.addDecoratorFactory(webSocketHandler ->
                new WebSocketHandlerDecorator(webSocketHandler) {
                    /**
                     * 客户端连接时触发
                     */
                    @Override
                    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                        System.out.println(session.getId() + "已连接");
                        super.afterConnectionEstablished(session);
                    }

                    /**
                     * 客户端断开连接时触发
                     */
                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        System.out.println(session.getId() + "已断开连接");
                        super.afterConnectionClosed(session, closeStatus);
                    }
                }
        );
    }

}
