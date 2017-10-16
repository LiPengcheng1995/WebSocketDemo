package com.myWebApp.web;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: lpc
 * @Description:
 * @Date: create in 14:50 2017/10/13
 * @Modified By:
 */
@Component
public class LogWebSocketHandler extends TextWebSocketHandler {
    /**
     * webscoket建立好链接之后的处理函数
     * @param webSocketSession 当前websocket的会话id，打开一个websocket通过都会生成唯一的一个会话，可以通过该id进行发送消息到浏览器客户端
     */
    //在线用户列表
    private static final Map<String, WebSocketSession> users;


    static {
        users = new HashMap<String, WebSocketSession>();
    }
    public LogWebSocketHandler() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("成功建立连接");
        String userName = (String) webSocketSession.getAttributes().get("userName");

        System.out.println(userName);
        if (userName != null) {
            users.put(userName, webSocketSession);
            webSocketSession.sendMessage(new TextMessage("成功建立socket连接"));
            System.out.println(userName);
            System.out.println(webSocketSession);
        }

    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        TextMessage textMessage = new TextMessage("用户"+getUserName(webSocketSession)+"已经下线");
        sendMessageToAllUsers(textMessage);
    }


    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){session.close();}
        users.remove(session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    private String getUserName(WebSocketSession webSocketSession) {
        Set<String> userNames = users.keySet();
        for (String userName:userNames) {
            if (users.get(userName).equals(webSocketSession)){
                return userName;
            }
        }
        return "";
    }
    /**
     * 发送信息给指定用户
     * @param
     * @param
     * @return
     */
    public boolean sendMessageToUser(String  userName, TextMessage message) {
        if (users.get(userName) == null) return false;
        WebSocketSession session = users.get(userName);
        System.out.println("sendMessage:" + session);
        if (!session.isOpen()) return false;
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 广播信息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> userNames = users.keySet();
        WebSocketSession session = null;
        for (String userName : userNames) {
            try {
                session = users.get(userName);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }

    public void sendMessageToUsers(ArrayList<String> userNames, String message) {

        for (String userName : userNames) {
            sendMessageToUser(userName,new TextMessage(message));
        }

    }

    public Set<String> getAllUserNames(){
        return users.keySet();
    }

}
