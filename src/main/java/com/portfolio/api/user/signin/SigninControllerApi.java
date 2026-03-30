package com.portfolio.api.user.signin;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.portfolio.api.JwtUtil;
import com.portfolio.common.LoginRequest;

@RestController
@RequestMapping("/api")
public class SigninControllerApi {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private static final Logger log =
        LoggerFactory.getLogger(SigninControllerApi.class);

    public SigninControllerApi(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public Map<String,String> apiSignin(@RequestBody LoginRequest request) {

        Authentication auth =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

        String token = jwtUtil.generateToken(request.getUsername());
        // log.info("Generated JWT: " + token);

        return Map.of("token", token);
    }
}