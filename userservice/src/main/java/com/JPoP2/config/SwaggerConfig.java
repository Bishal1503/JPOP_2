package com.JPoP2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.JPoP2.controller"))
                .paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo());
        docket.useDefaultResponseMessages(false);
        return docket;
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("User Service REST API").description("REST API documentation")
                .version("1.0.0").build();
    }
}
