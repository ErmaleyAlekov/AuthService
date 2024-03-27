package org.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        try {
            InetAddress local = InetAddress.getLocalHost();
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000", "http://" + local.getHostAddress().trim() + ":3000").allowedMethods("*");
        } catch (UnknownHostException e){
            registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("*");
        }
    }

}
