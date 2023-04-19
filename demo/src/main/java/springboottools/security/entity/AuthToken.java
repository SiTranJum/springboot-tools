package springboottools.security.entity;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class AuthToken {

    @JSONField(name="bu_guid")
    private String buGuid ;

    @JSONField(name="user_guid")
    private String userGuid ;

    @JSONField(name="jti")
    private String jwtId ;

    @JSONField(name="exp")
    private Date expire ;

    private String sign ;
}
