package com.foolish.schoolmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/courses").setViewName("courses");
    registry.addViewController("/courses/**").setViewName("404");
    registry.addViewController("/about").setViewName("about");
    registry.addViewController("/about/**").setViewName("404");
    registry.addViewController("/contact").setViewName("contact");
    registry.addViewController("/home/**").setViewName("404");
  }
}
