package com.foolish.schoolmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    // Disable CSRF đối với các API. Nhưng vẫn phải yêu cầu xác thực ROLE phù hợp với từng API.
    http.csrf((csrf) -> csrf.ignoringRequestMatchers("/api/**", "/public/**"))
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/dashboard").authenticated()
                    .requestMatchers("/display-profile").authenticated()
                    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/v1/student/**").hasRole("STUDENT")
                    .requestMatchers("/api/v1/user/**").permitAll()
                    .requestMatchers("/update-profile").authenticated()
                    .requestMatchers("/display-msg").hasRole("ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/student/**").hasRole("STUDENT")
                    .requestMatchers("/close-msg/**").hasRole("ADMIN")
                    .requestMatchers("/cart").hasRole("STUDENT")
                    .requestMatchers(
                            "/swagger-ui/**",
                            "/v1/api-docs/**",
                            "v2/api-docs/**",
                            "v3/api-docs/**").permitAll()
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().permitAll()
            )
            .passwordManagement(Customizer.withDefaults())
            .formLogin(loginConfig -> loginConfig.loginPage("/public/login").defaultSuccessUrl("/").failureUrl("/public/login?error=true").permitAll())
            .oauth2Login(Customizer.withDefaults())
            .logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/public/login?logout=true").invalidateHttpSession(true).permitAll())
            .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

}