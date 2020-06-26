# spring-session-data-redis

> 在`springboot`中使用`spring-session-data-redis`实现`http session`共享. `spring-session`共享的机制有很多,包括: `JDBC`,`MongoDB`,`Redis`,`Hazelcast`,`HashMap`, 本例用的是`redis`

## 使用

### 准备工作

1. idea
2. 创建一个本机可直接连接的`redis`服务
3. postman

### 启动

* `git clone git@github.com:DongCarzy/springboot-study.git` 下载项目
* `application.properties` 中配置自己的`redis`服务
* 启动项目,进行测试
    * mvn 方式,根目执行`mvn spring-boot:run` 启动项目
    * 配置 `idea` 启动
    * 直接main方法

## 开发

开发过程

### 引入相关JAR

> 仅仅简单演示,所以这里就只是引入`web`,`redis`,`spring-session`相关的JAR

1. `spring-boot-starter-web`建立一个`spring`+`mvc`的环境
2. `spring-session-data-redis`,今天的主角,同时它会帮我们引入`redis`的连接依赖`spring-data-redis`

### 配置

#### application.properties

> 如果你的redis服务在本机,且采用的默认配置.以下的步骤可以省略,下面的配置文档即为`springboot`的`默认`配置

```propertites
spring.redis.host=localhost
spring.redis.database=0
spring.redis.port=6379
```

#### 启动类

启动类上添加`EnableRedisHttpSession`开启`RedisHttpSession`功能,它会帮我们将`httpSession`存储到`redis`中

```java
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600, redisNamespace = "dxp")
public class SpringSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSessionApplication.class, args);
    }
}
```

`@EnableRedisHttpSession` 有三个参数,分别为 `maxInactiveIntervalInSeconds`, `redisNamespace`, `redisFlushMode` 并通过`@Import(RedisHttpSessionConfiguration.class)` 导入核心配置类`RedisHttpSessionConfiguration`,开启我们相关的功能

* `maxInactiveIntervalInSeconds` 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效, 默认`1800`
* `redisNamespace` 用于区分不同的项目，如果多个项目共用用户数据，可以采用相同的值，这样就简单的实现了统一登录效果. **当用户数据特别大,我们应该将缓存按照key值存储在不同的库.例如用户`session`数据存储在数据库1内，`业务缓存`存储在0内**
* `redisFlushMode` 设置redis保存session的方式, 默认`ON_SAVE`  
    有两种方式: `IMMEDIATE`:一旦创建session的时候就立即保存. `ON_SAVE`:创建session的时候不会保存,但当往session中添加数据的时候就会保存

### 编写测试DEMO

```java
@RestController
@RequestMapping(value = "/admin/v1")
public class QuickRun {
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("request Url"));
        return map;
    }
}
```

* `/admin/v1/first` 请将将当前请求的完整url放入到当前session的`attribute`中,其值为`request Url`
* `/admin/v1/sessions` 从当前session中取出的`attribute`中取出`request Url`,并返回.

### 测试

> 我使用的是`idea`, 启动的时候带上启动带上`server.port`, 值为`9090`. 也可以在 `application.properties` 中配置 `server.port=9090`. 默认值是 `8080`

配置中中限定了请求头中需要携带 `TOKEN`, 所以不论访问那个端口的服务，只要携带了相同的头，就认定为统一请求。  
`/admin/v1/session` 请求需要在请求时加入请求头 TOKEN

1. [初期化] 启动两个服务器,一个采用 `8080`, 一个使用 `9090`, 模拟后台负载均衡. 通过 `http://localhost:8080/admin/v1/first` 访问其中一个服务,并往`session`中添加部分属性值,返回: `{"request Url":"http://localhost:8080/admin/v1/first"}`
2. [同服务获取] 访问 `http://localhost:8080/admin/v1/session` 取出 session 中的值, 正常获取 `{"message":"http://localhost:8080/admin/v1/first","sessionId":"98ecbf89-57b3-49c7-a941-44ee4d7b6ca1"}`
3. [异服务获取] 另开一个标签页, 访问 `http://localhost:9090/admin/v1/session`, 模拟当前访问的是另外一台服务器,依旧正常拿到`session`,结果为`{"message":"http://localhost:8080/admin/v1/first","sessionId":"98ecbf89-57b3-49c7-a941-44ee4d7b6ca1"}`,课件两次请求是同一个`sessionID`
4. [异服务修改] 我们用 `9090` 服务请求 `first` 接口,修改 `session` 中的值,再通过 `8080` 端口取值,结果正常发生修改. `{"message":"http://localhost:9090/admin/v1/first","sessionId":"98ecbf89-57b3-49c7-a941-44ee4d7b6ca1"}`
5. [模拟多用户] 再次请求任意一个服务. 重复档次的测试.`sessionID`都发生变化
6. 本次测试中没有测试`session过期`和redisFlushMode的`IMMEDIATE`模式