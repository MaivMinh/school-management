package com.foolish.schoolmanagement.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/dashboard").authenticated()
                    .requestMatchers("/display-msg").hasRole("ADMIN")
                    .requestMatchers("/close-msg/**").hasRole("ADMIN")
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().permitAll()
            )
            .formLogin(loginConfig -> loginConfig.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error=true").permitAll())
            .logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
            .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("Minh@2000")
            .roles("USER")
            .build();
    UserDetails admin_1 = User.withDefaultPasswordEncoder()
            .username("admin1")
            .password("Minh@2000")
            .roles("USER", "ADMIN")
            .build();
    UserDetails admin_2 = User.withDefaultPasswordEncoder()
            .username("admin2")
            .password("Minh@2000")
            .roles("USER", "ADMIN")
            .build();
    return new InMemoryUserDetailsManager(user, admin_1, admin_2);
  }
}