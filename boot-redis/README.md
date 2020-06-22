# SpringBoot-Redis

> spring boot 整合 redis, 实现 redis 的基本存储以及订阅模式

## 流程

- clone code
- 安装 Redis
- 启动 项目

## 说明文档

### pom.xml

```xml
 <dependencies>
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-redis</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     <dependency>
         <groupId>redis.clients</groupId>
         <artifactId>jedis</artifactId>
         <type>jar</type>
         <scope>compile</scope>
     </dependency>
 </dependencies>
```

### 自定义序列化方案

springBoot的`redisTemplate`默认采用的JDK的序列化 `JdkSerializationRedisSerializer`， 可在源码`RedisTemplate.class` 中看到 `defaultSerializer` 的默认是正是 `JdkSerializationRedisSerializer` ,占用空间较大,且识别度不好.因此我们自定义. 这里采用的是 `GenericJackson2JsonRedisSerializer`
可以认为它是`jackson`的升级版本,在序列化的过程种会将Object的类型一起存储起来.  

#### 默认 `RedisTemplate` 声明在 `RedisAutoConfiguration.class` 这个类中

```java
@Configuration
@ConditionalOnClass({RedisOperations.class})
@EnableConfigurationProperties({RedisProperties.class})
@Import({LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class})
public class RedisAutoConfiguration {
    public RedisAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean(
        name = {"redisTemplate"}
    )
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}

```
#### 自定义方案

只定义其中一个即可满足大多数情况的使用,可根据自己的情况自行定义虚拟化方案,重点在替换 `setDefaultSerializer()`方法的值

```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnection) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnection);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        return redisTemplate;
    }

}
```  

未声明 `StringRedisTemplate`, 因为不需要更改 `StringRedisTemplate` 的序列化方案, 其次. `StringRedisTemplate` 是继承与 `RedisTemplate`, 只是他的 `KEY` 与 `value` 都是`string`  
此时基本的存储基本都满足了, 使用时只需要要加上如下代码即可: 

```
private RedisTemplate<String, Object> redisTemplate;

@Autowired
public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
}

// 使用
public Long pushObject(String key, Object source) {
    logger.debug("push object : {}", source);
    // 根据自己的情况选择合适的 存储方式
    // this.redisTemplate.opsForValue().set(key, source);
    return this.redisTemplate.opsForList().leftPush(key, source);
}
```

## 订阅模式

### 发布通道

**这里定义了一个发布消息的通道**

```java
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
```

通过我们的 `redisTemplate` 的 `convertAndSend()` 方法将我们的消息发送到固定的通道`这里定义了三个发布消息的通道`

- `RedisAllReceiver.java` : 用于接受所有的消息(匹配`topic_*` 通道的消息),在后面的配置文件可见到
 
 ```java
@Component
public class RedisAllReceiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(RedisAllReceiver.class);

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        Object itemValue = redisTemplate.getValueSerializer().deserialize(body);
        String topic = redisTemplate.getStringSerializer().deserialize(channel);
        logger.info("topic_*: {}, value: {}", topic, itemValue);
    }

}
```

- `RedisUserReceiver` 用于接受通道一的消息

```java
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
```

- `RedisUser2Receiver` 用于接受通道二的消息

```java
@Component
public class RedisUser2Receiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(RedisUser2Receiver.class);

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        Object itemValue = redisTemplate.getValueSerializer().deserialize(body);
        String topic = redisTemplate.getStringSerializer().deserialize(channel);
        logger.info("topic_user2: {}, value: {}", topic, itemValue);
    }

}
```

`下面是重点戏, 如何配置我们的监听`` 即上面定义的三个接收器的注入,回到我们的 `RedisConfig.java` 文件,加上我们的监听即可
     
```java
@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory redisConnection() {
        return new JedisConnectionFactory();
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory redisConnection) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnection);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer container(JedisConnectionFactory redisConnection,
                                                   RedisUserReceiver redisUserReceiver,
                                                   RedisUser2Receiver redisUser2Receiver,
                                                   RedisAllReceiver allReceiver) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnection);
        //  订阅了一个通道
        container.addMessageListener(redisUserReceiver, new PatternTopic(RedisChannel.USER_CHANNEL));
        container.addMessageListener(redisUser2Receiver, new PatternTopic(RedisChannel.USER2_CHANNEL));

        // 匹配多个  channel
        container.addMessageListener(allReceiver, new PatternTopic("topic_*"));
        return container;
    }

    public static class RedisChannel {
        public static final String USER_CHANNEL = "topic_user";
        public static final String USER2_CHANNEL = "topic_user2";
    }

}
```
- `MessageListener` 是我们的监听器,可定义多个: `RedisUserReceiver` redisUserReceiver, `RedisUser2Receiver` redisUser2Receiver, `RedisAllReceiver` allReceiver: 这里我定义了三个
- `RedisMessageListenerContainer` 存放我们所有的 监听器 `MessageListener`
   - 我定义个三个 `MessageListener` 前两个分别指向固定的 `channel` : `topic_user` 和 `topic_user2` , 而最后一个采用了 匹配符 `*`, 表示可匹配 `topic_` 开头的所有通道

## 如何使用

### 简易部分代码

```java
@RestController
public class RedisController {

    private final RedisService redisService;
    private final MessagePub messagePub;

    public RedisController(RedisService redisService, MessagePub messagePub) {
        this.redisService = redisService;
        this.messagePub = messagePub;
    }

    @GetMapping("/redis/string/push")
    public Long addRedis(@RequestParam(name = "value")String value) {
        return this.redisService.pushObject("redis-string", value);
    }

    @GetMapping("/redis/string/pop")
    public String getRedis() {
        return (String) this.redisService.popObject("redis-string");
    }

    @GetMapping("/redis/object/push")
    public Long addObjectRedis(@RequestParam(name = "id")int id,
                               @RequestParam(name = "no")String no,
                               @RequestParam(name = "name")String name) {
        User user = new User();
        user.setId(id);
        user.setNo(no);
        user.setName(name);
        String key = RedisKeyUtil.getKey("user", "id", String.valueOf(user.getId()));
        return this.redisService.pushObject(key, user);
    }

    @GetMapping("/redis/object/pop")
    public User getObjectRedis(@RequestParam(name = "id")int id) {
        String key = RedisKeyUtil.getKey("user", "id", String.valueOf(id));
        return (User) this.redisService.popObject(key);
    }

    /**
     * 以下代码测试 订阅模式
     */
    @GetMapping("/redis/message/pub")
    public void pubMessage(@RequestParam(name = "id")int id,
                           @RequestParam(name = "no")String no,
                           @RequestParam(name = "name")String name) {
        User user = this.setUser(id, no, name);
        this.messagePub.convertAndSend(RedisConfig.RedisChannel.USER_CHANNEL, user);
    }


    @GetMapping("/redis/message1/pub")
    public void pubMessage1(@RequestParam(name = "id")int id,
                           @RequestParam(name = "no")String no,
                           @RequestParam(name = "name")String name) {
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
```

### 说明

#### 对于 `RedisTemplate` 的测试

- 可将代码中的 `RedisService` 换成 `RedisTemplate`, 用 `RedisTemplate` 的方法即可, `RedisService` 为自己简单封装的一个service
- 可通过 `github` 查看源代码,直接 `copy` 下来即可

#### 对于 `订阅模式` 的测试

- 本地访问: `/redis/message/pub` API, 例如: `http://localhost:8080/redis/message/pub?id=1&no=1&name=1`
- 无论调用 `http://localhost:8080/redis/message/pub?id=1&no=1&name=1` 或者 `http://localhost:8080/redis/message1/pub?id=1&no=1&name=1`,会发现 `RedisUserReceiver` 和 `RedisUser2Receiver` 会成功接收到各自通道的消息, 而 `RedisAllReceiver` 会得到所有的消息
- 日志如下:

```
RedisUserReceiver   : topic: topic_user, value: User{id=1, no='1', name='1'}
RedisAllReceiver    : topic_*: topic_user, value: User{id=1, no='1', name='1'}
RedisUser2Receiver  : topic_user2: topic_user, value: User{id=1, no='1', name='1'}
RedisAllReceiver    : topic_*: topic_user2, value: User{id=1, no='1', name='1'}
```

