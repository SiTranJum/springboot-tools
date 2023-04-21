package security.webflux.demo.security;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//1、先查context有无（因为gateway就一台，直接本地缓存）
//2、后续如果需要存redis则使用这个仓库加载
public class JwtReactiveSecurityContextHolder implements ServerSecurityContextRepository {
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return ReactiveSecurityContextHolder.getContext();
    }
}
