package com.won.bookdomain.config.jwt;

import com.won.bookdomain.domain.User;
import lombok.experimental.UtilityClass;
import io.jsonwebtoken.*;
import org.springframework.data.util.Pair;

import java.security.Key;
import java.util.Date;

@UtilityClass
public class JwtUtils {
    public static String getUserName(String token) {
        return Jwts.parserBuilder()
                .setSigningKeyResolver(SigningKeyResolver.instance)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getName());
        Date now = new Date();
        Pair<String, Key> key = JwtKey.getRandomKey();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) // 토큰 만료 시간 설정
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid
                .signWith(key.getSecond()) // signature
                .compact();
    }
}
