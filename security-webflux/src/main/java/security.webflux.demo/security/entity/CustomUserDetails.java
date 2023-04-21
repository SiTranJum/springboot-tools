package security.webflux.demo.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class CustomUserDetails implements UserDetails {

    private User user;

    private String jwt;

    public CustomUserDetails(User user,String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: get authorities
        return AuthorityUtils.commaSeparatedStringToAuthorityList("");
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return jwt;
    }

    @Override
    public String getUsername() {
        return user.getGuid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}