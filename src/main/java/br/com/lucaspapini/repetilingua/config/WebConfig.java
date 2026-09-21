package br.com.lucaspapini.repetilingua.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Libera o acesso estático para a pasta "audios" na raiz do projeto
        registry.addResourceHandler("/audios/**")
                .addResourceLocations("file:./audios/");
    }
}
