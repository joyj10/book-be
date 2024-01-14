package com.won.bookdomain.config.jwt;

import com.won.bookdomain.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JWT 활용 로그인 인증
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        // 로그인할 때 입력한 email, password를 가지고 authenticationToken를 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getParameter("email"),
                request.getParameter("password"),
                new ArrayList<>()
        );
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 인증에 성공했을 때 사용
     * JWT Token을 생성해서 쿠키에 넣는다.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        User user = (User) authResult.getPrincipal();
        String token = JwtUtils.createToken(user);
        // 쿠키 생성
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, token);
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME); // 쿠키의 만료시간 설정
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/");
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException {
        response.sendRedirect("/login");
    }

}
