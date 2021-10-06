package com.bcp.reto.exchange.rate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class <b>WebMvcConfiguration</b>.
 * <p>Class for configurations of origins and headers </p>
 * @author Giancarlo
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private static final long MAX_AGE_SEC = 3600;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("*")
            .exposedHeaders("content-type", "Authorization")
            .maxAge(MAX_AGE_SEC);
  }
}
