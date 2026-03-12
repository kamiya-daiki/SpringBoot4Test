package com.portfolio.common;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
//import lombok.ToString;
import lombok.Value;

import com.portfolio.common.JwtFilter;
import com.portfolio.user.signin.SigninSuccessHandler;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

    public ApiSecurityConfig(SigninSuccessHandler signinSuccessHandler) {
        this.signinSuccessHandler = signinSuccessHandler;
    }

    @Autowired
    JwtFilter jwtFilter;
    
    @Autowired
    private SigninSuccessHandler signinSuccessHandler;

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
                .requestMatchers("/api/auth/login").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}
