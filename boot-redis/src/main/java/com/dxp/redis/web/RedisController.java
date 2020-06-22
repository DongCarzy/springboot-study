package com.dxp.redis.web;

import com.dxp.redis.config.RedisConfig;
import com.dxp.redis.modal.User;
import com.dxp.redis.redis.MessagePub;
import com.dxp.redis.redis.RedisKeyUtil;
import com.dxp.redis.redis.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试 redis 是否成功
 * 非标准的 restful api
 *
 * @author carzy
 */
@RestController
public class RedisController {

    private final RedisService redisService;
    private final MessagePub messagePub;

    public RedisController(RedisService redisService, MessagePub messagePub) {
        this.redisService = redisService;
        this.messagePub = messagePub;
    }

    @GetMapping("/redis/string/push")
    public Long addRedis(@RequestParam(name = "value") String value) {
        return this.redisService.pushObject("redis-string", value);
    }

    @GetMapping("/redis/string/pop")
    public String getRedis() {
        return (String) this.redisService.popObject("redis-string");
    }

    @GetMapping("/redis/object/push")
    public Long addObjectRedis(@RequestParam(name = "id") int id,
                               @RequestParam(name = "no") String no,
                               @RequestParam(name = "name") String name) {
        User user = new User();
        user.setId(id);
        user.setNo(no);
        user.setName(name);
        String key = RedisKeyUtil.getKey("user", "id", String.valueOf(user.getId()));
        return this.redisService.pushObject(key, user);
    }

    @GetMapping("/redis/object/pop")
    public User getObjectRedis(@RequestParam(name = "id") int id) {
        String key = RedisKeyUtil.getKey("user", "id", String.valueOf(id));
        return (User) this.redisService.popObject(key);
    }

    /**
     * 以下代码测试 订阅模式
     */
    @GetMapping("/redis/message/pub")
    public void pubMessage(@RequestParam(name = "id") int id,
                           @RequestParam(name = "no") String no,
                           @RequestParam(name = "name") String name) {
        User user = this.setUser(id, no, name);
        this.messagePub.convertAndSend(RedisConfig.RedisChannel.USER_CHANNEL, user);
    }


    @GetMapping("/redis/message1/pub")
    public void pubMessage1(@RequestParam(name = "id") int id,
                            @RequestParam(name = "no") String no,
                            @RequestParam(name = "name") String name) {
        User user = this.setUser(id, no, name);
        this.messagePub.convertAndSend(RedisConfig.RedisChannel.USER2_CHANNEL, user);
    }

    private User setUser(int id, String no, String name) {
        User user = new User();
        user.setId(id);
        user.setNo(no);
        user.setName(name);
        return user;
    }

}
