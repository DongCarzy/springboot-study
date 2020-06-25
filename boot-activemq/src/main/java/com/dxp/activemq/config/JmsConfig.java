package com.dxp.activemq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @author carzy
 */
@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        return new JmsTemplate(connectionFactory);
    }

    @Bean("jmsQueueListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置连接数
        factory.setConcurrency("3-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        factory.setPubSubDomain(false);
        factory.setAutoStartup(true);
        return factory;

    }

    /**
     * 普通订阅
     */
    @Bean("jmsTopicListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setRecoveryInterval(1000L);
        factory.setPubSubDomain(true);
        factory.setAutoStartup(true);
        return factory;
    }

    /**
     * 持久订阅
     */
    @Bean("jmsTopicListenerContainerFactory2")
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory2(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setRecoveryInterval(1000L);
        factory.setPubSubDomain(true);

        // 给订阅者一个名字,并开启持久订阅
        factory.setClientId("client_id");
        factory.setSubscriptionDurable(true);
        factory.setAutoStartup(true);
        return factory;
    }

}
