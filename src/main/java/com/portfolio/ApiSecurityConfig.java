package com.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.portfolio.api.JwtFilter;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {

        http
            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            )
            .securityMatcher("/api/**")
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 認可が不要なページ
                .requestMatchers(
                    "/api/signin",
                    "/api/signup",
                    "/api/signup-action"
                ).permitAll()
                // その他のページは全て認可が必要
                .anyRequest().authenticated());

        return http.build();
    }
}
