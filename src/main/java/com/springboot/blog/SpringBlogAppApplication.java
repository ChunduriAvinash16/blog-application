package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog REST APIs",
				description = "Spring Boot Blog REST APIs documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Avinash",
						email = "chunduriavinash16@gmail.com",
						url = "https://github.com/ChunduriAvinash16"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/ChunduriAvinash16"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog REST APIs documentation",
				url = "https://github.com/ChunduriAvinash16"
		)
)
public class SpringBlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
