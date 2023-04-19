package springboottools.security;

import org.springframework.stereotype.Service;
import springboottools.domain.User;
import springboottools.security.entity.CustomUserDetails;

@Service
public class SSOLoginExecutor{

    public SSOAuthenticationToken usernamePasswordLogin(SSOAuthenticationToken authToken) {
        String username = (String) authToken.getPrincipal();
        String password = (String) authToken.getCredentials();
        // TODO: 2023/4/10 构造 CustomUserDetails对象（1、调用SSO登录接口拿去token，查询yser表获取user数据）
        CustomUserDetails principalToReturn = new CustomUserDetails(new User(),"123");
        return new SSOAuthenticationToken(principalToReturn, authToken.getCredentials(), principalToReturn.getAuthorities());
    }

    public SSOAuthenticationToken qrCodeLogin(SSOAuthenticationToken authToken) {
        return null;
    }

    public SSOAuthenticationToken wxLogin(SSOAuthenticationToken authToken) {
        return null;
    }
}
