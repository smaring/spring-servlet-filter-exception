package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

  @Autowired
  private SpringTemplateEngine templateEngine;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .anonymous(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            .addFilterBefore( new FilterChainExceptionHandlerFilter( templateEngine ), DisableEncodeUrlFilter.class )
            .addFilterBefore( new SampleFilter(), SecurityContextHolderFilter.class )
            .build();
  }

}
