package com.won.bookdomain.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@OpenAPIDefinition(
		info = @Info(
				title = "Book Project",
				description = "책 리뷰 프로젝트 API 명세서",
				version = "v1"))
@Configuration
public class SwaggerConfig {
	private static final String PREFIX = "Authorization";

	@Bean
	public OpenAPI openAPI(){
		SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

		return new OpenAPI()
				.components(new Components().addSecuritySchemes("bearerAuth", securityScheme()))
				.security(Collections.singletonList(securityRequirement));
	}

	private static SecurityScheme securityScheme() {
		return new SecurityScheme()
				.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
				.in(SecurityScheme.In.HEADER).name(PREFIX);
	}
}
