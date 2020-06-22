package com.dxp.redis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis 消息发送者
 *
 * @author carzys
 */
@Service
public class MessagePub {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void convertAndSend(String channel, Object msg) {
        this.redisTemplate.convertAndSend(channel, msg);
    }
}
