
package security.webflux.demo.security.exceptionHandler;

import com.nwcs.bop.gateway.security.utils.ResultCode;
import com.nwcs.bop.gateway.security.utils.WebFluxUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * jwt auth fail point
 **/


@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

//    @Override
//    public void commence(HttpServletRequest httpServletRequest,
//                         HttpServletResponse httpServletResponse,
//                         AuthenticationException e) throws IOException, ServletException {
//        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
//        HashMap<String, String> map = new HashMap<>(2);
//        map.put("uri", httpServletRequest.getRequestURI());
//        map.put("msg", "认证失败");
//        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String resBody = objectMapper.writeValueAsString(map);
//        PrintWriter printWriter = httpServletResponse.getWriter();
//        printWriter.print(resBody);
//        printWriter.flush();
//        printWriter.close();
////        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "login failed");
//    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        log.error(e.getMessage());
        Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> WebFluxUtils.writeResponse(response, ResultCode.UNAUTHORIZED));
        return mono;
    }
}
