package com.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.portfolio.common.JwtFilter;
// import com.portfolio.user.signin.SigninSuccessHandler;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    // public ApiSecurityConfig(SigninSuccessHandler signinSuccessHandler) {
    //     this.signinSuccessHandler = signinSuccessHandler;
    // }

    // @Autowired
    // private SigninSuccessHandler signinSuccessHandler;

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
