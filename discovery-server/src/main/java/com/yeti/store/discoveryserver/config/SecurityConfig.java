package com.yeti.store.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Value("${eureka.username}")
  private String username;
  @Value("${eureka.password}")
  private String password;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.
      csrf().disable()
      .authorizeHttpRequests(auth -> auth
                                    .anyRequest()
                                    .authenticated()
                          )
                          .httpBasic()
                          // .and()
                          // .formLogin()
                          ;
    return http.build();
  }

  @Bean
     public UserDetailsService userDetailsService() {
         UserDetails user = User.withDefaultPasswordEncoder()
             .username("eureka")
             .password("password")
             .roles("USER")
             .build();
         return new InMemoryUserDetailsManager(user);
     }
      
}
