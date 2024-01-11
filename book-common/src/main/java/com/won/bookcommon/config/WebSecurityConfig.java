package com.won.bookcommon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private static final String[] whitelist = {
			"/api/login",
			"/api/logout",
			"/api/v3/api-docs",
			"/api/configuration/ui",
			"/api/swagger-resources/*",
			"/api/configuration/security",
			"/api/swagger-ui.html",
			"/api/swagger-ui/",
			"/api/swagger-ui/*"
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// basic authentication
		http.httpBasic(withDefaults());
		// csrf
		http.csrf(AbstractHttpConfigurer::disable);
		// authorization
		http.authorizeHttpRequests(requests -> requests
						.requestMatchers(whitelist).permitAll()
						.anyRequest().authenticated());
		// login
		http.formLogin(AbstractHttpConfigurer::disable);
		return http.build();
	}
}
