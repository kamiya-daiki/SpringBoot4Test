package com.portfolio.user.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
public class SignupControllerApi {

    private final SignupUserService signupUserService;
    private static final Logger log =
        LoggerFactory.getLogger(SignupControllerApi.class);

    @PostMapping("/api/signup-action")
    public String signup(
            @RequestParam String username,
            @RequestParam String password
    ) {
        try
        {
            log.info("SignupControllerApi: signup called start : username=" + username);
            signupUserService.createUser(username, password);
            log.info("SignupControllerApi: signup called end : username=" + username);
        } 
        catch (Exception e) 
        {
            log.info(e.getMessage());
            return "/signup?error_signup=Error_creating_user";
        }

        return "/signin";
    }
}