package springboottools.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SSOAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SSOLoginExecutor ssoLoginExecutor;

    private Map<SSOLoginType, Function<SSOAuthenticationToken,SSOAuthenticationToken>> ssoLoginTypeMap=new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SSOAuthenticationToken authToken = (SSOAuthenticationToken) authentication;

        return ssoLoginTypeMap.get(authToken.getSsoLoginType()).apply(authToken);
//        String principalToReturn = null;
//        // TODO: 2023/4/7 策略模式
//        switch (authToken.getSsoLoginType()) {
//            case Password:
//                // 在这里实现自定义的身份验证逻辑
//                principalToReturn = (String) authentication.getPrincipal();
//            case QrCode:
//
//
//        }
//
//        return new SSOAuthenticationToken(principalToReturn, authentication.getCredentials(), new SSOLoginResult().getAuthorities());
    }

    @PostConstruct
    public void init() {
        ssoLoginTypeMap.put(SSOLoginType.Password,authPre -> ssoLoginExecutor.usernamePasswordLogin(authPre));
        ssoLoginTypeMap.put(SSOLoginType.QrCode,authPre -> ssoLoginExecutor.qrCodeLogin(authPre));
        ssoLoginTypeMap.put(SSOLoginType.WX,authPre -> ssoLoginExecutor.wxLogin(authPre));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(SSOAuthenticationToken.class);
    }

}