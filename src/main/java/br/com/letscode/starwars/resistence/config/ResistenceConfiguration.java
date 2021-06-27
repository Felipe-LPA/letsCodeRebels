package br.com.letscode.starwars.resistence.config;

import java.util.Collections;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(SpringDataRestConfiguration.class)
@ComponentScan(basePackages = { ResistenceConstants.APP_BASE_PACKAGE })
@EnableJpaRepositories(basePackages = ResistenceConstants.REPOSITORY_BASE_PACKAGE)
@EntityScan({ ResistenceConstants.ENTITY_BASE_PACKAGE })
public class ResistenceConfiguration {
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()
          .apis(RequestHandlerSelectors.basePackage(ResistenceConstants.APP_BASE_PACKAGE))
          .paths(PathSelectors.any())                          
          .build()
          .useDefaultResponseMessages(false)
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "Star Wars Resistence Social Network API", 
          "Rede social da resistência ao império.", 
          "1.0.0", 
          "", 
          new Contact("Bruno Pereira Pinho", "http://www.linkedin.com/in/brunoppinho/", "bruno.pereira.pinho@gmail.com"), 
          "", "", Collections.emptyList());
    }


}
