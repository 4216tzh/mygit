package com.java.springboot.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
/**
 * websocket的配置类
 * @author tzh
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry endpoint) {
		/**
		 * 注册一个名字为‘my-websocket’消息连接点,在网页上就可以通过这个消息连接点来及时通信
		 * SockJs是一个WebSocket的通信js库
		 */
		endpoint.addEndpoint("/my-websocket").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/**
		 * 这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址
		 * 
		 */
		registry.enableSimpleBroker("/topic");
		
		/**
		 * 设置了一个应用程序访问地址的前缀，目的估计是为了和其他的普通请求区分开吧
		 * 也就是说，网页上要发送消息到服务器上的地址是/startApplication/send
		 */
		registry.setApplicationDestinationPrefixes("/startApplication");
	}
	
	

}
