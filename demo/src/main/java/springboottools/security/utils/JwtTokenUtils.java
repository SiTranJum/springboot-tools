package springboottools.security.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import springboottools.security.SSOAuthenticationToken;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static io.jsonwebtoken.Claims.EXPIRATION;


/**
 * Jwt token tool
 *
 * @author wfnuser
 */
@Component
public class JwtTokenUtils {

    private final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    private static final String AUTHORITIES_KEY = "auth";

    /**
     * secret key
     */
    private String secretKey;

    /**
     * Token validity time(ms)
     */
    private long tokenValidityInMilliseconds;

    @PostConstruct
    public void init() {
        this.secretKey = "SecretKey012345678901234567890123456789012345678901234567890123456789";
        this.tokenValidityInMilliseconds = 1000 * 60 * 30L;
    }

    /**
     * Create token
     *
     * @param authentication auth info
     * @return token
     */
    public String createToken(String subject) {
        /**
         * Current time
         */
        long now = (new Date()).getTime();
        /**
         * Validity date
         */
        Date validity;
        validity = new Date(now + this.tokenValidityInMilliseconds);

        /**
         * create token
         */
        return Jwts.builder()
            .setSubject(subject)
            .claim(AUTHORITIES_KEY, "")
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }
    /**
     * Create token
     *
     * @param authentication auth info
     * @return token
     */
    public String createToken(Authentication authentication) {
        return this.createToken(authentication.getName());
    }


    /**
     * Get auth Info
     *
     * @param token token
     * @return auth info
     */
    public Authentication  getAuthentication(String token) {
        /**
         *  parse the payload of token
         */
        Claims claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES_KEY));


        User principal = new User(claims.getSubject(), "", authorities);
        return new SSOAuthenticationToken(principal, "", authorities);
    }

    /**
     * validate token
     *
     * @param token token
     * @return whether valid
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    public void refleshToken(String token, HttpServletResponse response) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        LocalDateTime expiration = LocalDateTimeUtil.of((Integer) claims.get(EXPIRATION));
        //如果剩下10分钟token就过期则刷新token
        if (expiration.minusMinutes(10).isBefore(LocalDateTime.now())) {
            String jwtSub = claims.getSubject();
            response.setHeader("jwt", this.createToken(jwtSub));
        } else {
            response.setHeader("jwt", token);
        }

    }
}
