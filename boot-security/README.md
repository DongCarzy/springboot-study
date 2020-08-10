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

- ChannelProcessingFilter
- ConcurrentSessionFilter
- WebAsyncManagerIntegrationFilter
- SecurityContextPersistenceFilter
- HeaderWriterFilter
- CorsFilter
- CsrfFilter
- LogoutFilter
- OAuth2AuthorizationRequestRedirectFilter
- Saml2WebSsoAuthenticationRequestFilter
- X509AuthenticationFilter
- AbstractPreAuthenticatedProcessingFilter
- CasAuthenticationFilter
- OAuth2LoginAuthenticationFilter
- Saml2WebSsoAuthenticationFilter
- UsernamePasswordAuthenticationFilter
- ConcurrentSessionFilter
- OpenIDAuthenticationFilter
- DefaultLoginPageGeneratingFilter
- DefaultLogoutPageGeneratingFilter
- DigestAuthenticationFilter
- BearerTokenAuthenticationFilter
- BasicAuthenticationFilter
- RequestCacheAwareFilter
- SecurityContextHolderAwareRequestFilter
- JaasApiIntegrationFilter
- RememberMeAuthenticationFilter
- AnonymousAuthenticationFilter
- OAuth2AuthorizationCodeGrantFilter
- SessionManagementFilter
- ExceptionTranslationFilter
- FilterSecurityInterceptor
- SwitchUserFilter

## 核心类

### Authentication

Authentication 是一个接口，用来表示用户认证信息的，在用户登录认证之前相关信息会封装为一个 Authentication 具体实现类的对象. 认证成功后会丰富这个对象,并将其交给 `SecurityContextHolder` 所持有的 `SecurityContext` 中

### SecurityContextHolder

SecurityContextHolder 是用来保存 SecurityContext 的。SecurityContext 中含有当前正在访问系统的用户的详细信息。默认情况下，SecurityContextHolder 将使用 ThreadLocal 来保存 SecurityContext.

## ajax登录

新增 `AjaxAuthenticationFilter` 来处理登录请求中,ajax携带的登录信息,在写 out response 时,没有加跨域,如果需要跨域,则在 `AbstractFilterResponseOutPrint` 中写对应的跨域响应头.

## JWT

`JwtTokenFilter` 负责解析请求头中的 `token` 字段,并获取对应的用户信息以及权限信息,如果未过期,则在security上下文 `SecurityContextHolder` 中塞入认证信息,
否则交给一下一个认证器.

- 未实现token刷新,可以单独开一个接口,让前端去通过那个接口刷新token的过期时间.
 