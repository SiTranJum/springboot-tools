package springboottools.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class SSOLoginResult {

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: get authorities
        return AuthorityUtils.commaSeparatedStringToAuthorityList("");
    }
}
