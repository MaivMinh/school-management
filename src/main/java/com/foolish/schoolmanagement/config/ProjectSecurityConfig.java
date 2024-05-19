package com.foolish.schoolmanagement.config;

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
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/dashboard", "/contact").authenticated()
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
    UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("Minh@2000")
            .roles("USER", "ADMIN")
            .build();
    return new InMemoryUserDetailsManager(user, admin);
  }


}