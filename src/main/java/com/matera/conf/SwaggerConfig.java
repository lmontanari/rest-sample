package com.matera.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration("applicationSwaggerConfig")
public class SwaggerConfig {

   @Bean
   public Docket docket() {
      Docket docket = new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2);

      return docket.apiInfo(apiInfo());
   }

   private ApiInfo apiInfo() {
      ApiInfo apiInfo = new ApiInfo(
            "User REST API", "API Documentation for user crud.",
            "0.0.1",
            "urn:tos",
            new Contact("Lucas", "www.matera.com", "lucas.montanari@matera.com"),
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0");

      return apiInfo;
   }
}
