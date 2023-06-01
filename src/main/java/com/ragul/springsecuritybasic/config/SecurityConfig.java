package com.ragul.springsecuritybasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/add").permitAll()
                .requestMatchers("/api/v1/user/update/**", "api/v1/user/delete/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return http.build();
    }
}
