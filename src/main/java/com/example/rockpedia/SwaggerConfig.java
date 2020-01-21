package com.example.rockpedia;

import com.example.rockpedia.band.Band;
import io.swagger.annotations.ApiModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.rockpedia.band"))
                .build()
                .apiInfo(metaData());

    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Rockpedia")
                .description("\"L'API qui fait du bruit\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses")
                .build();
    }

    @ApiModel()
    public interface BandsList extends List<Band> {
        // This is a shim class that serves to generate a meaningful swagger spec
        // SEE https://github.com/swagger-api/swagger-core/issues/498#issuecomment-73732315
    }
}
