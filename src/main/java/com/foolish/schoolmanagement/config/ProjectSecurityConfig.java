package com.foolish.schoolmanagement.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/dashboard").authenticated()
                    .requestMatchers("/display-profile").authenticated()
                    .requestMatchers("/update-profile").authenticated()
                    .requestMatchers("/display-msg").hasRole("ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/student/**").hasRole("STUDENT")
                    .requestMatchers("/close-msg/**").hasRole("ADMIN")
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().permitAll()
            )
            .passwordManagement(Customizer.withDefaults())
            .formLogin(loginConfig -> loginConfig.loginPage("/public/login").defaultSuccessUrl("/").failureUrl("/public/login?error=true").permitAll())
            .logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/public/login?logout=true").invalidateHttpSession(true).permitAll())
            .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

}