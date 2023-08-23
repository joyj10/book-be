package com.won.book.core.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	public void configure(WebSecurity web) throws Exception
//	{
//		web.ignoring().antMatchers("/v2/api-docs",
//				"/configuration/ui",
//				"/swagger-resources/**",
//				"/configuration/security",
//				"/swagger-ui.html",
//				"/swagger-ui/",
//				"/swagger-ui/**",
//				"/api/**",     //TODO api 패스는 삭제 필요
//				"/webjars/**",
//				"/logout");
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http
//				.httpBasic().disable()
//				.csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/login", "/swagger-ui.html", "/swagger-resources/**","/swagger-ui/","/swagger-ui/**", "/api/**").permitAll() //TODO api 패스는 삭제 필요
//				.anyRequest().authenticated();
////				.and()
////				.formLogin()
////				.loginPage("/user/login")
////				.permitAll()
////				.and()
////				.logout()
////				.permitAll();
//
//
////				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////				.and()
////                        .antMatchers("/**").hasRole("USER")
////				.antMatchers("/api/token/**").permitAll()
////				.antMatchers("/api/**").hasRole("USER")
////				.and()
////                    .addFilterBefore(new JwtLoginFilter("/login", authenticationManager(), tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
////				.logout()
////                    .logoutUrl("/logout")
////				.invalidateHttpSession(true);
//	}
//}


