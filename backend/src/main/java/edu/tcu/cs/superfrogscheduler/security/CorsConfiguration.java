package edu.tcu.cs.superfrogscheduler.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173", "https://superfrogscheduler-69tmp0f7b-dpham123occs-projects.vercel.app", "https://superfrog-container-frontend.nicerock-3516d100.eastus.azurecontainerapps.io") // Allow this origin to make requests
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Include cookies in the requests; // Enable CORS for the whole application.
            }
        };
    }
}