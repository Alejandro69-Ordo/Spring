package com.example.demo;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Configuracion_Swagger {

	@Bean
	public Docket clienteApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
			.paths(regex("/api.*"))
			.build()
			.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		ApiInfo api= new ApiInfo(
				"PROYECTO API REST",
				"API REST",
				"V 1.0.0",
				"Terminos de servicio",
				new Contact("Felix Ordoñez","alejo28199@gmail.com","096384999"),
				"Visual Studio",
				"https://www.apache.org/licesen.html",new ArrayList<VendorExtension>()
				);
		return api;
	}
}
