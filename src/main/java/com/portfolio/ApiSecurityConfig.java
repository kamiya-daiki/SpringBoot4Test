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

    // @Bean
    // PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public AuthenticationManager authenticationManager(
    //     AuthenticationConfiguration config) throws Exception {

    //     return config.getAuthenticationManager();
    // }

    @Bean
    SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http
            .securityMatcher("/api/**")
            // CSRF(クロスサイトリクエストフォージェリ)保護
            .csrf(csrf -> csrf.disable())
            // セッション管理を無効化(ステートレス)
            .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 認可が不要なページ
                .requestMatchers(
                    "/api/signin-action",
                    "/js/**",
                    "/bootstrap/**",
                    "/bootstrap-examples/**"
                ).permitAll()
                // その他のページは全て認可が必要
                .anyRequest().authenticated()
            );

            // // フォームログイン設定
            // .formLogin(form -> form
            //     .loginPage("/")                         // 画面表示
            //     .loginProcessingUrl("/signin-action")   // 認証処理
            //     .failureHandler((request, response, exception) -> {
            //         response.sendRedirect("/signin-action?error_signin=" + 
            //             URLEncoder.encode("failed_to_signin", StandardCharsets.UTF_8));
            //     })
            //     .successHandler(signinSuccessHandler)
            //     .permitAll()
            // )
            // // ログアウト設定
            // .logout(logout -> logout
            //     // ログアウト用エンドポイント
            //     .logoutUrl("/logout")
            //     // 成功後の遷移先
            //     .logoutSuccessUrl("/")
            //     // セッションの無効化
            //     .invalidateHttpSession(true)
            //     // クッキーの削除
            //     .deleteCookies("JSESSIONID")
            //     .permitAll()
            // );

            http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
