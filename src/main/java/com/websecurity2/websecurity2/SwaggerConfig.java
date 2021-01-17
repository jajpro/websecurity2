package com.websecurity2.websecurity2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //확인 URL
    //http://localhost:8080/swagger-ui.html
    //http://localhost:8080/v2/api-docs
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())   // 현재 RequestMapping으로 할당된 모든 URL 리스트 추출
                //.paths(PathSelectors.ant("/api/**"))  // 그 중 /api/** 인 URL들만 필터링
                .build();
    }

}