
package security.webflux.demo.security;

import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import security.webflux.demo.security.utils.JwtTokenUtils;

import java.io.IOException;

/**
 * jwt auth token filter
 *
 * @author wfnuser
 */
public class JwtAuthenticationTokenFilter implements WebFilter, Ordered {

    private static final String TOKEN_PREFIX = "Bearer ";

    private JwtTokenUtils tokenProvider;

    public JwtAuthenticationTokenFilter(JwtTokenUtils tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        Authentication authentication = null;
        String jwt = resolveToken(request);
        if (jwt != null && !"".equals(jwt.trim())
//              &&  ReactiveSecurityContextHolder.getContext().map(context -> context.getAuthentication()).block() == null
        ) {
            if (this.tokenProvider.validateToken(jwt)) {
                /**
                 * jwt续期
                 */
                this.tokenProvider.refleshToken(jwt,response);
                /**
                 * 根据jwt获取用户凭证
                 */
                authentication = this.tokenProvider.getAuthentication(jwt);
                /**
                 * 存储凭证到securityContext
                 */
//                ReactiveSecurityContextHolder.getContext().doOnNext(context -> context.setAuthentication(authentication));
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
            }
        }


        return chain.filter(exchange);
    }

    /**
     * Get token from header
     */
    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(SecurityConfig.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        String jwt = request.getQueryParams().getFirst(SecurityConfig.AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(jwt)) {
            return jwt;
        }
        return null;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
