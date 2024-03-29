package springboottools.security;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboottools.security.entity.CustomUserDetails;
import springboottools.security.utils.JwtTokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;


    @PostMapping("/login-by-password")
    public R<CustomUserDetails> login(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String accessToken = "";
        CustomUserDetails user = null;
        try {
            // 1 创建SSOAuthenticationToken
            SSOAuthenticationToken usernameAuthentication = new SSOAuthenticationToken(username, password, SSOLoginType.Password);

            // 2 认证
            Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);

            // 3 保存认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 4 生成自定义token
            accessToken = jwtTokenUtils.createToken(authentication);


            user = (CustomUserDetails)authentication.getPrincipal();

            // 5 user放入缓存
//        redisTemplate.opsForValue().set(user.getUsername(),JSON.toJSON(user).toString());
        } catch (BadCredentialsException authentication) {
            return new R<CustomUserDetails>().setMsg("认证失败");
        }

        //将Token写入到Http头部
        R<CustomUserDetails> r = new R<>();
        response.addHeader(SecurityConfig.AUTHORIZATION_HEADER, "Bearer " + accessToken);
        r.setCode(200);
        r.setData(user);
        return r;

    }

}
