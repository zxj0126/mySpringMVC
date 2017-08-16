package com.myspringmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Tricky
 * @version 2017年8月14日
 */
@WebAppConfiguration
@EnableSwagger2
public class SwaggerConfig {
	@SuppressWarnings("deprecation")
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.securitySchemes(Lists.newArrayList(new ApiKey("api_key", "token", "header") ) )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Swagger REST API Documentation", 
                        "SpringMvc", 
                        "1.0.0",
                        "",
                        "Tricky", 
                        "", 
                        ""));
	}
}
