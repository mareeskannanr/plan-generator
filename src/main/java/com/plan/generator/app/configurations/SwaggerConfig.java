package com.plan.generator.app.configurations;

import com.plan.generator.app.utils.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration to expose plan generator webservice through Swagger UI
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        //Excluding ResponseEntity Model definition in swagger
        Class[] clazz = {ResponseEntity.class};

        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage(AppConstants.CTRL_PACKAGE))
                .build()
                .ignoredParameterTypes(clazz)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(AppConstants.TITLE).description(AppConstants.DESCRIPTION).version(AppConstants.VERSION).build();
    }
}