package com.booking.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
	    public Docket docket(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .pathMapping("/")
	                .select()
	                .apis(
	            RequestHandlerSelectors.basePackage("com.booking.hotel.controller"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(
	                    new ApiInfoBuilder()
	                    .title("Spring Security take over Swagger Authentication authorization")
	                    .description("Spring Security and Swagger")
	                    .version("1.0.0")
	                    .contact(
	                        new Contact(
	                            "Olgun YILDIZ",
	                            "",
	                            "olgun.yldz@gmail.com"
	                        )
	                    ).build()
	                );
	    }
}