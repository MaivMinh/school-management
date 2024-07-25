package com.foolish.schoolmanagement.config;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Class chứa tất cả cấu hình cho việc Consuming Another REST Service
@Configuration
public class ProjectConfiguration {
  @Bean
  public RestTemplate restTemplate() {
    RestTemplateBuilder restTemplateBuilder =
            new RestTemplateBuilder();
    return restTemplateBuilder.basicAuthentication
            ("maivanminh.se@gmail.com", "admin").build();
  }
}