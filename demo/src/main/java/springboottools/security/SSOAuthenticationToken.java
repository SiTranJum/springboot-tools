package springboottools.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import springboottools.security.entity.CustomUserDetails;

import java.util.Collection;

public class SSOAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;
    private Object credentials;

    private SSOLoginType ssoLoginType;

    public SSOAuthenticationToken(Object principal, Object credentials,SSOLoginType ssoLoginType) {
        super((Collection)null);
        this.principal = principal;
        this.credentials = credentials;
        this.ssoLoginType = ssoLoginType;
        this.setAuthenticated(false);
    }

    public SSOAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public SSOLoginType getSsoLoginType() {
        return this.ssoLoginType;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

    @Override
    public String getName() {
        if (this.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails)this.getPrincipal()).getPassword();
        }
        return super.getName();
    }
}
