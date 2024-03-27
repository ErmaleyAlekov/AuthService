package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class ServiceApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ServiceApp.class, args);
        String status = "Service started";
        Logger.addLog(status);
        while(app.isRunning()){
            status = "Service started";
        }
        status = "Service stopped";
        Logger.addLog(status);
    }
    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}