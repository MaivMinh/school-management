package com.foolish.schoolmanagement;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.foolish.schoolmanagement.repo")
@EntityScan("com.foolish.schoolmanagement.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SchoolManagementApplication {
  public static void main(String[] args) {
    SpringApplication.run(SchoolManagementApplication.class, args);
  }
}