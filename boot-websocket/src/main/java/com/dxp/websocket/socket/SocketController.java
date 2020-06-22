package com.dxp.websocket.socket;

import com.dxp.websocket.socket.vo.RecMessage;
import com.dxp.websocket.socket.vo.SendMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试websocket通信
 *
 * @author carzy
 * @date 2020/6/22
 */
@RestController
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/echo")
    @SendTo("/topic/echo")
    public SendMessage greeting(RecMessage message) throws Exception {
        System.out.println(message);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setType(message.getType());
        sendMessage.setData(message.getData());
        return sendMessage;
    }

    /**
     * 每秒推送一次.
     * 需要开启定时器任务. @EnableScheduling
     *
     * @see org.springframework.scheduling.annotation.EnableScheduling
     */
    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/balance")
    public void balance() {
        this.simpMessagingTemplate.convertAndSend("/topic/balance", dtf.format(LocalDateTime.now()));
    }

}
