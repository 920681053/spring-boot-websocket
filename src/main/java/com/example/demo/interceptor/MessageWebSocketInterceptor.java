package com.example.demo.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author YuanJian
 * @data 18-3-26下午3:18
 * 继承HttpSessionHandshakeInterceptor对象。该对象作为页面连接websocket服务的拦截器，可以在websocket建立之间和之后做一些事情。重载beforeHandshake和afterHandshake
 */
public class MessageWebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.err.println("****************** beforeHandshake ******************");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        System.err.println("****************** afterHandshake ******************");
    }
}