//package com.nwcs.bop.gateway.security.utils;
//
//
//import cn.hutool.core.util.StrUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.auth0.jwt.JWT;
//import com.nwcs.lam.bop.security.entity.AuthToken;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Base64;
//
//public class AuthTokenUtils {
//
//    private final Logger log = LoggerFactory.getLogger(AuthTokenUtils.class);
//
//    public static AuthToken parse(String authToken) {
//        if (StringUtils.isEmpty(authToken) || !authToken.contains(".")) {
//            return new AuthToken();
//        }
//
//        String[] authTokenArr = authToken.split("\\.");
//        if (authTokenArr.length != 3) {
//            return new AuthToken();
//        }
//
//        String staffEncode = authTokenArr[1];
//        String staffJson;
//        try {
//            staffJson = new String(Base64.getUrlDecoder().decode(staffEncode.getBytes()), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e) ;
//        }
//        AuthToken result = new AuthToken() ;
//        JSONObject staffMap = JSONObject.parseObject(staffJson);
//
//        result.setBuGuid(staffMap.getString("bu_guid"));
//        result.setUserGuid(staffMap.getString("staff_guid"));
//        result.setJwtId(staffMap.getString("jti"));
//        result.setExpire(staffMap.getDate("exp"));
//        //result.setSign("sign", authTokenArr[2]);
//        result.setSign(authTokenArr[2]);
//        return result;
//    }
//
//    /**
//     * 获取值
//     *
//     * @param token
//     * @param name
//     * @return
//     */
//    public static String get(String token, String name) {
//        if (StrUtil.isBlank(token) || StrUtil.isBlank(name)) {
//            return StrUtil.EMPTY;
//        }
//        try {
//            //如默认为字符串使用String.valueOf，会返回""91""这个格式，转化导致异常
//            String result = JWT.decode(token).getClaim(name).asString();
//            //兼容如果为long和int类型返回为空的异常
//            if (StrUtil.isEmpty(result)) {
//                result = String.valueOf(JWT.decode(token).getClaim(name));
//            }
//            return result;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return StrUtil.EMPTY;
//    }
//
//    /**
//     * 获取sub的值
//     *
//     * @param token
//     * @return
//     */
//    public static String getUsernameFromToken(String token) {
//        if (StrUtil.isBlank(token)) {
//            return StrUtil.EMPTY;
//        }
//        try {
//            return JWT.decode(token).getSubject();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return StrUtil.EMPTY;
//    }
//
//    /**
//     * 获取请求的token
//     *
//     * @param bearerToken
//     * @return
//     */
//    public static String resolveToken(String bearerToken, String tokenStartWith) {
//        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith(tokenStartWith)) {
//            // 去掉令牌前缀
//            return bearerToken.replace(tokenStartWith, "");
//        }
//        return bearerToken;
//    }
//
////    /**
////     * Get auth Info
////     *
////     * @param token token
////     * @return auth info
////     */
////    public Authentication getAuthentication(String token) {
////        /**
////         *  parse the payload of token
////         */
////        Claims claims = Jwts.parser()
////                .setSigningKey(secretKey)
////                .parseClaimsJws(token)
////                .getBody();
////
////        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES_KEY));
////
////
////        User principal = new User(claims.getSubject(), "", authorities);
////        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
////    }
////
////
////    /**
////     * validate token
////     *
////     * @param token token
////     * @return whether valid
////     */
////    public boolean validateToken(String token) {
////        try {
////            String sign = MD5Utils.md5Hex(token.getBytes());
////            return true;
////        } catch (SignatureException e) {
////            log.info("Invalid JWT signature.");
////            log.trace("Invalid JWT signature trace: {}", e);
////        } catch (MalformedJwtException e) {
////            log.info("Invalid JWT token.");
////            log.trace("Invalid JWT token trace: {}", e);
////        } catch (ExpiredJwtException e) {
////            log.info("Expired JWT token.");
////            log.trace("Expired JWT token trace: {}", e);
////        } catch (UnsupportedJwtException e) {
////            log.info("Unsupported JWT token.");
////            log.trace("Unsupported JWT token trace: {}", e);
////        } catch (IllegalArgumentException e) {
////            log.info("JWT token compact of handler are invalid.");
////            log.trace("JWT token compact of handler are invalid trace: {}", e);
////        }
////        return false;
////    }
//
//}
