package com.dxp.activemq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 队列监听
 * <p>
 * 同一个消息只会被消费一次,即 receive,receive2虽然监听的是同一个队列,但是不会消费同一个消息.
 *
 * @author carzy
 * @date 2020/06/24
 */
@Component
public class QueueReceiver {

    @JmsListener(destination = "queueTest", containerFactory = "jmsQueueListenerContainerFactory")
    public void receive(String msg) {
        System.out.println("queue1 监听到的消息内容为: " + msg);
    }

    @JmsListener(destination = "queueTest", containerFactory = "jmsQueueListenerContainerFactory")
    public void receive2(String msg) {
        System.out.println("queue2 监听到的消息内容为: " + msg);
    }

}
