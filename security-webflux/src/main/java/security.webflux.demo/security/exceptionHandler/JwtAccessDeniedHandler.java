package security.webflux.demo.security.exceptionHandler;

import com.nwcs.bop.gateway.security.utils.ResultCode;
import com.nwcs.bop.gateway.security.utils.WebFluxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class JwtAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        log.error(denied.getMessage());
        Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> WebFluxUtils.writeResponse(response, ResultCode.FORBIDDEN));
        return mono;
    }
}
