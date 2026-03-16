package com.portfolio.common;

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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // @Autowired
    // private final AuthenticationManager authenticationManager;

    // @Autowired
    // private final JwtUtil jwtUtil;

    // @Autowired
    // public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    //     this.authenticationManager = authenticationManager;
    //     this.jwtUtil = jwtUtil;
    // }

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private static final Logger log =
        LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequest request) {

        log.info("AuthController: start");
        Authentication auth =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

        log.info("AuthController: start2");
        String token = jwtUtil.generateToken(request.getUsername());

        log.info("Generated JWT: " + token);
        log.info("AuthController: end");

        return Map.of("token", token);
    }
}