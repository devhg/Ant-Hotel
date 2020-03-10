package com.anthotel.web.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: Devhui
 * @Date: 2020/3/10 13:09
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketService {

    public final static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);

    private String videoRecServerHost = "10.90.7.10";

    private int videoRecServerPort = 7667;

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketService> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    private static final WebSocketService instance = new WebSocketService();

    public static final WebSocketService getInstance() {
        return instance;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            //加入set中
        } else {
            webSocketMap.put(userId, this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        logger.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }

    @OnMessage
    public void onBinaryMessage(ByteBuffer message, Session session, boolean last) throws IOException, InterruptedException {
        byte[] sentBuf = message.array();

        logger.info("Binary Received: " + sentBuf.length + ", last: " + last);

        //下面的代码逻辑，是用UDP协议发送视频流数据到视频处理服务器做后续逻辑处理
//        sendToVideoRecognizer(sentBuf);
        BufferedWriter out = null;
        File file = new File("C:\\Users\\Devhui\\Desktop\\faceData\\zgh.jpg");
        FileOutputStream fos = new FileOutputStream(file);

        fos.write(sentBuf);
        fos.close();
    }

    private void sendToVideoRecognizer(byte[] sentBuf) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress addr = InetAddress.getByName(videoRecServerHost);
        DatagramPacket sendPacket = new DatagramPacket(sentBuf, sentBuf.length, addr, videoRecServerPort);
        client.send(sendPacket);
        client.close();
    }


    @OnClose
    public void onClose() {
        webSocketMap.remove("udpSocket");
        logger.info("client onclose");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("connection onError");
    }

    public boolean sendMessage(String message, String staffId) throws IOException {
        logger.info("即时通讯，发送到客户端的消息为 : " + message);
        WebSocketService client = webSocketMap.get(staffId);
        try {
            if (client == null) {
                return false;
            }
            client.session.getBasicRemote().sendText(message);
            return true;
        } catch (IOException e) {
            logger.info("发送消息到客户端失败 ：" + e.getMessage());
            try {
                client.session.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }
}
