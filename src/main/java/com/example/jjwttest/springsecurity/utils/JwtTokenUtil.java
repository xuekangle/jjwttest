package com.example.jjwttest.springsecurity.utils;

import com.example.jjwttest.springsecurity.entity.JwtUser;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;

/**
 * Created by echisan on 2018/6/23
 */
@Slf4j
public class JwtTokenUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "emptyxxx";
    private static final String ISS = "xxx";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 创建token
    public static String createToken(JwtUser user) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
    }

    // 从token中获取用户名
    public static String getUsername(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (UsernameNotFoundException e) {
            log.error("获取username失败！");
            username = null;
        }
        return username;
    }

    // 是否已过期
    public static boolean isExpiration(String token){
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            log.error("JWT expired!");
            return false;
        }
    }

    //解析JWT
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ClaimJwtException e){
            log.error("解析 JWT 错误！");
            claims = null;
        }
        return claims;
    }

    /**
     * 验证JWT
     */
    public static Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        String username = getUsername(token);

        return (username.equals( user.getUsername() ) && !isExpiration(token));
    }

}
