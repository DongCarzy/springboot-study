package com.dxp.redis.redis.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author carzy
 */
@Component
public class RedisUserReceiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(RedisUserReceiver.class);

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        Object itemValue = redisTemplate.getValueSerializer().deserialize(message.getBody());
        String topic = redisTemplate.getStringSerializer().deserialize(message.getChannel());
        logger.info("topic: {}, value: {}", topic, itemValue);
    }
}
