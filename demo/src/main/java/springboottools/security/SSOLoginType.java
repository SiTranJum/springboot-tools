package springboottools.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SSOLoginType {

    Password(0, "账号密码登录"),
    QrCode(1, "二维码登录"),
    WX(2, "微信登录");

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 描述
     */
    private final String description;

}
