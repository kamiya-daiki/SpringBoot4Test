package com.portfolio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.portfolio.user.signin.SigninSuccessHandler;

@Configuration
@EnableWebSecurity
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
                .requestMatchers(
                    // 認可が不要なページ
                    "/",
                    "/index",
                    "/index.html",
                    "/signin",
                    "/signup"
                    // "/css/**",
                    // "/js/**",
                    // "/images/**"
                )
                .permitAll()
                .anyRequest()
                // その他のページは全て認可が必要
                .authenticated()
            )
            // フォームログイン設定
            .formLogin(form -> form
                .loginPage("/signin")
                .failureUrl("/index?message_signin=failed_to_signin")
                .successHandler(signinSuccessHandler)
                .permitAll()
            )
            // ログアウト設定
            .logout(logout -> logout
                .logoutUrl("/logout") // ログアウト用エンドポイント
                .logoutSuccessUrl("/") // 成功後の遷移先
                .invalidateHttpSession(true) // セッションの無効化
                .deleteCookies("JSESSIONID") // クッキーの削除
                .permitAll()
            );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
