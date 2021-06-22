package com.phillrocha.forum.config.swagger;

import com.phillrocha.forum.models.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// E usaremos o @Configuration do Spring que é pra ele carregar essa classe de configuração
@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket forumApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.phillrocha.forum"))
                    .paths(PathSelectors.ant("/**"))
                    .build()
                    .ignoredParameterTypes(Usuario.class);
    }
}
