package com.accenture.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /*
     * Starter configuration:
     * replace permitAll() with the rules described in README.md.
     * Keep CSRF enabled for browser forms and decide how API routes
     * should be handled based on the authentication mechanism you choose.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll());

        return http.build();
    }
}
