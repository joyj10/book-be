package com.won.bookdomain.config.jwt;

import lombok.experimental.UtilityClass;

/**
 * JWT 기본 설정값
 */
@UtilityClass
public class JwtProperties {
    public static final int EXPIRATION_TIME = 600000; // 10분
    public static final String COOKIE_NAME = "JWT-AUTHENTICATION";
}
