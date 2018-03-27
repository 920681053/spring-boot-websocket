package com.example.demo.config;

import com.example.demo.handler.MessageWebSocketHandler;
import com.example.demo.interceptor.MessageWebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author YuanJian
 * @data 18-3-26下午3:16
 * 继承WebSocketConfigurer需要重写registerWebSocketHandlers方法，指明handler和interceptor。
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * 配置websocket入口，允许访问的域、注册Handler、SockJs支持和拦截器
     * 1.registry.addHandler注册和路由的功能，当客户端发起websocket连接，把/path交给对应的handler处理，而不实现具体的业务逻辑，可以理解为收集和任务分发中心。
     * 2.setAllowedOrigins(String[] domains),允许指定的域名或IP(含端口号)建立长连接，如果只允许自家域名访问，这里轻松设置。如果不限时使用"*"号，如果指定了域名，则必须要以http或https开头。
     * 3.addInterceptors，是为handler添加拦截器，可以在调用handler前后加入我们自己的逻辑代码。
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageWebSocketHandler(), "/message")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor(), messageWebSocketInterceptor());
    }

    @Bean
    public MessageWebSocketHandler messageWebSocketHandler() {
        return new MessageWebSocketHandler();
    }

    @Bean
    public MessageWebSocketInterceptor messageWebSocketInterceptor() {
        return new MessageWebSocketInterceptor();
    }
}