package br.com.lucaspapini.repetilingua.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Para o Spring Carregar essa Classe quando estiver inicializando
public class OepnApiConfig {
    @Bean
        // Objeto montado e gerenciado pelo proprio Spring
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API RESTful para o aplicativo de aprendizado em inglês")
                        .version("v0.0.1")
                        .description("REST API RESTful para gerenciamento de conteúdo em ingles para aprendizagem com repetição e  repetição espaçada.")
                        .termsOfService("https://github.com/lucasPapini")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/lucasPapini")
                        )
                );

    }
}
