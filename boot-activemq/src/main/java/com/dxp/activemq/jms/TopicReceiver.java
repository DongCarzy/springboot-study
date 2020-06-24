package com.dxp.activemq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 订阅监听
 *
 * @author carzy
 */
@Component
public class TopicReceiver {

    @JmsListener(destination = "topic.test", containerFactory = "jmsTopicListenerContainerFactory")
    public void receive(String msg) {
        System.out.println("topicTest1 监听到的消息内容为: " + msg);
    }

    @JmsListener(destination = "topic.test", containerFactory = "jmsTopicListenerContainerFactory")
    public void receive2(String msg) {
        System.out.println("topicTest2 监听到的消息内容为: " + msg);
    }

}
