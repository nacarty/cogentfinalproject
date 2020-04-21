package com.carty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	//IN ADDITION TO USING POSTMAN, YOU MAY ACCESS FROM - http://localhost:8080/swagger-ui.html - BECAUSE WE USED SWAGGER
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();  //ApiInfo instead of apiInfo
		}
	
	public Predicate<String> postPaths(){
		return or(regex("api/posts.*"), regex("/api.*"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Books API")
				.description("Books in my Library")
				.contact("nacarty@gmail.com").license("My Book License")
				.licenseUrl("nacarty@gmail.com").version("1.0.0").build();
	}
}
