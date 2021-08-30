package com.app.devlanches.api.documentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).select()
				.apis(RequestHandlerSelectors.basePackage("com.app.devlanches.api.controller"))
				.paths(PathSelectors.any()).build().securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey())).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Dev Lanches").description("Gerenciamento de pedidos").version("1.0")
				.contact(contact()).build();
	}

	private Contact contact() {
		return new Contact("Clesyo", "https://github.com/Clesyo/devlanches", "clesyo.siva@gmail.com");
	}

	public ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverrything");

		AuthorizationScope[] scopes = new AuthorizationScope[1];
		scopes[0] = scope;

		SecurityReference reference = new SecurityReference("JWT", scopes);

		List<SecurityReference> auths = new ArrayList<>();
		auths.add(reference);

		return auths;

	}
}
