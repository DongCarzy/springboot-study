package com.dxp.activemq.web;

import com.dxp.activemq.config.constant.AmqDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carzy
 * @date 2018/08/01
 */
@RestController
public class TestController {

    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsMessagingTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("jms/queue")
    public void jmsQueueTemplate(@RequestParam String value) {
        this.jmsTemplate.convertAndSend(AmqDestination.QUEUE_TEST, value);
    }

    @GetMapping("jms/topic")
    public void jmsTopicTemplate(@RequestParam String value) {
        this.jmsTemplate.convertAndSend(AmqDestination.TOPIC_TEST, value);
    }

}
