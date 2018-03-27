package com.lhfs.ensqlrest.javaspringrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhfs.ensqlrest.javaspringrest"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/branch/*"))
                //.paths(PathSelectors.ant("/loanofficer/*"))
                .paths(regex("/uat.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "LOCC API (WEB-D1-UAT)",
                "LOCC - APIs",
                "1.1.4",
                "Terms of Service",
                new springfox.documentation.service.Contact("LHFS", "https://yourdomain100.com/apiinfo", "noreply@yourdomain100.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"

        );

        return apiInfo;
    }

}
