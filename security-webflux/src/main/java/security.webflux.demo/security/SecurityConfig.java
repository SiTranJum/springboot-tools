package security.webflux.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import security.webflux.demo.security.exceptionHandler.JwtAccessDeniedHandler;
import security.webflux.demo.security.exceptionHandler.JwtAuthenticationEntryPoint;
import security.webflux.demo.security.utils.JwtTokenUtils;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig{

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String AUTHORIZATION_TOKEN = "access_token";

//    @Autowired
//    private JwtTokenUtils jwtTokenProvider;

    /**
     * 配置哪些请求是否需要过滤以及配置自定义的过滤器等等
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         JwtTokenUtils tokenProvider,
                                                         ReactiveAuthenticationManager reactiveAuthenticationManager,
                                                         ReactiveAuthorizationManager<AuthorizationContext> authorizationManager) {http
                //关闭csrf
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                //关闭httpBasic认证
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                // 开启跨域以便前端调用接口
                .cors().and()
                //注入认证信息存储管理仓库(是否有默认的？)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                //todo 注入自定义的认证管理器
                .authenticationManager(reactiveAuthenticationManager)

                .exceptionHandling()
                //注入认证失败响应端点
                .authenticationEntryPoint(authenticationEntryPoint())
                //注入未授权响应带端点
                .accessDeniedHandler(accessDeniedHandler())
                .and()


                //注入授权交换机
                .authorizeExchange()
                //权限白名单(WebFlux的ant通配符发生了变化,不能用/**)  "/*/*"
                .pathMatchers(HttpMethod.POST,"/bop/login/by-password", "/register/*")
                .permitAll()
                //todo 鉴权管理器配置
                .anyExchange().access(authorizationManager);

        //注入JWT认证过滤器
        http.addFilterAt(new JwtAuthenticationTokenFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC);
        //禁用session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        return http.build();
    }

    /**
     * 自定义未授权响应
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    /**
     * token无效或者已过期自定义响应
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    ServerSecurityContextRepository serverSecurityContextRepository() {
        return new JwtReactiveSecurityContextHolder();
    }

    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        return new SSOReactiveAuthenticationManager();
    }

    @Bean
    ReactiveAuthorizationManager<AuthorizationContext> authorizationManager(){
        return new MyAuthorizationManager();
    }

}
