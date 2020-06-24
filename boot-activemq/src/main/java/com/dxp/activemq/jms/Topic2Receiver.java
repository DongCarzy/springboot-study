package com.dxp.activemq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

/**
 * 可监听历史消息
 * <p>
 * 持久订阅通道,否是是持久订阅由监听者的 containerFactory 控制.
 *
 * @author carzy
 * @date 2018/08/01
 * @see DefaultJmsListenerContainerFactory#setSubscriptionDurable 设置为 true
 */
@Component
public class Topic2Receiver {

    @JmsListener(destination = "topicTest", containerFactory = "jmsTopicListenerContainerFactory2")
    public void receive(String msg) {
        System.out.println("这是持久订阅: " + msg);
    }

}
