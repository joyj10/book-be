package com.won.bookdomain.config;

import com.won.bookdomain.config.jwt.JwtAuthenticationFilter;
import com.won.bookdomain.config.jwt.JwtAuthorizationFilter;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.repository.UserRepository;
import com.won.bookdomain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final UserService userService;
	private final UserRepository userRepository;
	private final AuthenticationConfiguration authenticationConfiguration;
	private static final String[] whitelist = {
			"/api/login",
			"/api/logout",
			"/api/v3/api-docs",
			"/api/configuration/ui",
			"/api/swagger-resources/**",
			"/api/configuration/security",
			"/api/swagger-ui.html",
			"/api/swagger-ui/",
			"/api/swagger-ui/**",
			"/api/book/**",
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/api/v3/api-docs/**",
			"/api/swagger-ui/**",
			"/api/v3/api-docs",
			"/api/swagger-ui"
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// basic authentication
		http.httpBasic(Customizer.withDefaults());
		// csrf
		http.csrf(AbstractHttpConfigurer::disable);
		// stateless
		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// jwt filter
		http.addFilterBefore(
				new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration)),
				UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(
				new JwtAuthorizationFilter(userRepository),
				BasicAuthenticationFilter.class
		);
		// authorization
		http.authorizeHttpRequests(requests -> requests
						.requestMatchers(whitelist).permitAll()
						.requestMatchers("/api/**").permitAll()
						.anyRequest().authenticated());
		// login
		http.formLogin(AbstractHttpConfigurer::disable);
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return email -> {
			User user = userService.findByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException(email);
			}
			return user;
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
