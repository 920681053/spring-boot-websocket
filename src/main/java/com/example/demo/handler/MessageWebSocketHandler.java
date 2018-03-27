package com.example.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author YuanJian
 * @data 18-3-26下午3:19
 * 继承WebSocketHandler对象。该对象提供了客户端连接,关闭,错误,发送等方法,重写这几个方法即可实现自定义业务逻辑
 */
@Service
@Slf4j
public class MessageWebSocketHandler extends AbstractWebSocketHandler {

    public static final ConcurrentMap<String, WebSocketSession> map = new ConcurrentHashMap<>();

    /**
     * 连接成功时候，会触发页面上onopen方法
     *
     * @param session
     */
    public void afterConnectionEstablished(WebSocketSession session) {
        map.put(session.getId(), session);
        log.info("connect to the websocket success......当前数量:" + map.size());
    }

    /**
     * 接受并处理客户端发送的消息
     *
     * @param session
     * @param message
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("[{}] Processing {}", session.getId(), message);
        sendMessage(message);
    }

    /**
     * 连接关闭后处理
     *
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        super.afterConnectionClosed(session, closeStatus);
        map.remove(session.getId(), session);
        log.info("[{}] Session is closed", session.getId());
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessage(TextMessage message) {
        log.info("send Message [{}]", message);
        Set<Map.Entry<String, WebSocketSession>> entries = map.entrySet();
        for (Map.Entry<String, WebSocketSession> entrie : entries) {
            try {
                WebSocketSession session = entrie.getValue();
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}