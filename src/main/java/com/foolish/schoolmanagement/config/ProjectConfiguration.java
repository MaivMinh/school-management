package com.foolish.schoolmanagement.config;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

// Class chứa tất cả cấu hình cho việc Consuming Another REST Service
@Configuration
public class ProjectConfiguration {

  private final Environment environment;

  @Autowired
  public ProjectConfiguration(Environment environment) {
    this.environment = environment;
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplateBuilder restTemplateBuilder =
            new RestTemplateBuilder();
    return restTemplateBuilder.basicAuthentication
            ("maivanminh.se@gmail.com", "admin").build();
  }

  @Bean
  public Cloudinary cloudinaryConfig() {
    Cloudinary cloudinary = null;
    Map config = new HashMap();
    config.put("cloud_name", environment.getProperty("cloudinary.cloud_name"));
    config.put("api_key", environment.getProperty("cloudinary.api_key"));
    config.put("api_secret", environment.getProperty("cloudinary.api_secret"));
    cloudinary = new Cloudinary(config);
    return cloudinary;
  }
}