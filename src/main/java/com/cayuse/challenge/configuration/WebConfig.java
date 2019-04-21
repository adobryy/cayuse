package com.cayuse.challenge.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);
    private static final String[] ALL = {"*"};

    @PostConstruct
    public void init() {
        LOGGER.info("WebConfig init PostConstruct");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins( ALL ) // simplified setting for JS based web component
                .allowedMethods("GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE") // For future development
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600); // for tocken based Auth. in future
    }
}
