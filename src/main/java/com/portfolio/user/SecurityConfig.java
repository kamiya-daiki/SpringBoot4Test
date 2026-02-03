package com.portfolio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.portfolio.user.login.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // CSRF(クロスサイトリクエストフォージェリ)保護
            .csrf(csrf -> csrf.disable())

            // 認可設定
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/index",
                    "/login",
                    "/signup"
                    // "/css/**",
                    // "/js/**",
                    // "/images/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
            )
            // フォームログイン設定
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(loginSuccessHandler)
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
