# SPRINGBOOT-SECURITY

springboot 项目中采用 `security` 作为系统权限管控.

## security流程

进入与出来的过程相反

```flow
start=>start: client
operation0=>operation: Filter0
operation2=>operation: Filter2
operationProxy=>operation: DelegationgFilterProxy
servlet=>operation: Servlet
securityFilterChain=>subroutine: SecurityFilterChain

start->operation0
operation0->operationProxy
operationProxy->securityFilterChain
securityFilterChain->operation2
operation2->servlet
```

以下是spring安全过滤器排序的完整列表：

- 通道处理过滤器
- 并发隔离筛选器
- WebAsyncManager 整合过滤器
- 安全配置保持过滤器
- 标题编写器
- 科尔斯菲尔特
- CsrfFilter
- 注销过滤器
- OAuth2 授权重新授权重定向
- Saml2WebSsoAuthentication 请求过滤器
- X509纯化过滤器
- 抽象正加工过滤器
- 卡萨森蒂特过滤器
- OAuth2LoginAuththationfilter
- Saml2WebSsoAuththationfilter
- [`UsernamePasswordAuthenticationFilter`](https://docs.spring.io/spring-security/site/docs/5.3.3.BUILD-SNAPSHOT/reference/html5/#servlet-authentication-usernamepasswordauthenticationfilter)
- 并发隔离筛选器
- OpenIDAuthationfilter
- 默认登录页面生成筛选器
- 默认登录页面生成筛选器
- [`DigestAuthenticationFilter`](https://docs.spring.io/spring-security/site/docs/5.3.3.BUILD-SNAPSHOT/reference/html5/#servlet-authentication-digest)
- 记者托肯· 奥森特菲尔特
- [`BasicAuthenticationFilter`](https://docs.spring.io/spring-security/site/docs/5.3.3.BUILD-SNAPSHOT/reference/html5/#servlet-authentication-basic)
- 请求缓存软件过滤器
- 安全配置库软件请求过滤器
- 贾萨皮整合过滤器
- 记住我AuthenationFilter
- 匿名纯化过滤器
- OAuth2 授权代码格兰特过滤器
- 会话管理过滤器
- [`ExceptionTranslationFilter`](https://docs.spring.io/spring-security/site/docs/5.3.3.BUILD-SNAPSHOT/reference/html5/#servlet-exceptiontranslationfilter)
- [`FilterSecurityInterceptor`](https://docs.spring.io/spring-security/site/docs/5.3.3.BUILD-SNAPSHOT/reference/html5/#servlet-authorization-filtersecurityinterceptor)
- 交换机用户筛选

## 核心类

### Authentication

Authentication 是一个接口，用来表示用户认证信息的，在用户登录认证之前相关信息会封装为一个 Authentication 具体实现类的对象. 认证成功后会丰富这个对象,并将其交给 `SecurityContextHolder` 所持有的 `SecurityContext` 中

### SecurityContextHolder

SecurityContextHolder 是用来保存 SecurityContext 的。SecurityContext 中含有当前正在访问系统的用户的详细信息。默认情况下，SecurityContextHolder 将使用 ThreadLocal 来保存 SecurityContext.