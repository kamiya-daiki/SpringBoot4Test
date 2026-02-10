package com.portfolio.user.signin;

import java.io.IOException;
import java.time.Instant;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.portfolio.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Component
public class SigninSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public SigninSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        System.out.println("SigninSuccessHandler: start");
        userRepository.updateLastLoginDatetime(authentication.getName(), Instant.now());
        System.out.println("SigninSuccessHandler: end");

        response.sendRedirect(request.getContextPath() + "/home");
    }
}


