package security.webflux.demo.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

public class MyAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        return Mono.just(new AuthorizationDecision(false));

//        return authentication
//                .filter(a -> a.isAuthenticated())
//                .flatMapIterable( a -> a.getAuthorities())
//                .map( g-> g.getAuthority())
//                .any(c->{
//                    //检测权限是否匹配
//                    String[] roles = c.split(",");
//                    for(String role:roles) {
//                        if(authorities.contains(role)) {
//                            return true;
//                        }
//                    }
//                    return false;
//                })
//                .map( hasAuthority -> new AuthorizationDecision(hasAuthority))
//                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
