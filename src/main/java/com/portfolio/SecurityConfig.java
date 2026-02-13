package com.portfolio;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.portfolio.user.signin.SigninSuccessHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Autowired
    private SigninSuccessHandler signinSuccessHandler;

    public SecurityConfig(SigninSuccessHandler signinSuccessHandler) {
        this.signinSuccessHandler = signinSuccessHandler;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // CSRF(クロスサイトリクエストフォージェリ)保護
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // 認可が不要なページ
                .requestMatchers(
                    "/",
                    "/index",
                    "/index.html",
                    "/index.css",
                    "/signin",
                    "/signin/api/**",
                    "/signup",
                    "/css/**",
                    "/js/**",
                    "/bootstrap/**",
                    "/bootstrap-examples/**"
                ).permitAll()
                // その他のページは全て認可が必要
                .anyRequest().authenticated()
            )
            // フォームログイン設定
            .formLogin(form -> form
                .loginPage("/")                // 画面表示
                .loginProcessingUrl("/signin")      // 認証処理
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/index?error_signin=" + 
                        URLEncoder.encode("failed_to_signin", StandardCharsets.UTF_8));
                })
                .successHandler(signinSuccessHandler)
                .permitAll()
            )
            // ログアウト設定
            .logout(logout -> logout
                // ログアウト用エンドポイント
                .logoutUrl("/logout")
                // 成功後の遷移先
                .logoutSuccessUrl("/")
                // セッションの無効化
                .invalidateHttpSession(true)
                // クッキーの削除
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
