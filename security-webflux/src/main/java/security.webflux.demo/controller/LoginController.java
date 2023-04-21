package security.webflux.demo.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import security.webflux.demo.security.SSOAuthenticationToken;
import security.webflux.demo.security.SSOLoginType;
import security.webflux.demo.security.entity.CustomUserDetails;
import security.webflux.demo.security.utils.JwtTokenUtils;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/bop/login")
public class LoginController {

    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

//    @Autowired
//    private StringRedisTemplate redisTemplate;


    @PostMapping("/by-password")
    public Mono<R<CustomUserDetails>> login(@RequestParam String username, @RequestParam String password) {

//        String username = request.queryParam("username").get();
//        String password = request.queryParam("password").get();

        String accessToken = "";
        CustomUserDetails user = null;
        try {
            // 1 创建SSOAuthenticationToken
            SSOAuthenticationToken usernameAuthentication = new SSOAuthenticationToken(username, password, SSOLoginType.Password);

            // 2 认证
            Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication).block();

            // 3 保存认证信息
            ReactiveSecurityContextHolder.getContext().doOnNext(context -> context.setAuthentication(authentication));

            // 4 生成自定义token
            accessToken = jwtTokenUtils.createToken(authentication);


            user = (CustomUserDetails)authentication.getPrincipal();

            // 5 user放入缓存
//        redisTemplate.opsForValue().set(user.getUsername(),JSON.toJSON(user).toString());
        } catch (BadCredentialsException authentication) {
            return Mono.just(new R<CustomUserDetails>().setMsg("认证失败"));
        }

        if (user == null) {

        }

        //将Token写入到Http头部
        R<CustomUserDetails> r = new R<>();
//        response.addHeader(SecurityConfig.AUTHORIZATION_HEADER, "Bearer " + accessToken);
        user.setJwt(accessToken);
        r.setCode(200);
        r.setData(user);
        return Mono.just(r);

    }

}
