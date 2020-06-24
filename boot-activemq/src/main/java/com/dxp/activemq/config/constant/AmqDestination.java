package com.dxp.activemq.config.constant;

import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.Destination;

/**
 * activemq发送目的地定义
 *
 * @author carzy
 * @date 2020/6/24
 */
public class AmqDestination {

    /**
     * 队列
     */
    public static final Destination QUEUE_TEST = new ActiveMQQueue("queue.test");

    /**
     * 订阅
     */
    public static final Destination TOPIC_TEST = new ActiveMQQueue("topic.test");
}
