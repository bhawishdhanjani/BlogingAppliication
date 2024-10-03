package com.bhawish.blog.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.core.jackson.PathsSerializer;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	private Info getInfo() {

		return new Info().title("Blogging Application").description("This developed by Bhavesh Kumar")
				.termsOfService("Terms & Service")
				.contact(new Contact().email("bhawishdhanajanii@gmail.com").name("Bhavesh Kumar"));
	}

	@Bean
	public OpenAPI caseOpenAPI() {
		 return new OpenAPI().info(getInfo()).addSecurityItem(new SecurityRequirement().
		            addList("Bearer Authentication"))
			        .components(new Components().addSecuritySchemes
			            ("Bearer Authentication", createAPIKeyScheme()));
	}
	
	private SecurityScheme createAPIKeyScheme() {
	    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
	        .bearerFormat("JWT")
	        .scheme("bearer");
	}
}
