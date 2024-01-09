package com.won.bookcommon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/**").authenticated()
                        .anyRequest().permitAll()
                );

        return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(
				"/api/v3/api-docs",
				"/api/configuration/ui",
				"/api/swagger-resources/**",
				"/api/configuration/security",
				"/api/swagger-ui.html",
				"/api/swagger-ui/",
				"/api/swagger-ui/**",
				"/webjars/**",
				"/api/**",
				"/logout"
		);
	}
}
