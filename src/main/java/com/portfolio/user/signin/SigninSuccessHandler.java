package com.portfolio.user.signin;

import java.io.IOException;
import java.time.Instant;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import com.portfolio.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SigninSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public SigninSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private static final Logger log =
        LoggerFactory.getLogger(SigninSuccessHandler.class);

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        log.info("SigninSuccessHandler: start");
        userRepository.updateLastLoginDatetime(authentication.getName(), Instant.now());
        log.info("SigninSuccessHandler: end");

        response.sendRedirect(request.getContextPath() + "/home");
    }
}


