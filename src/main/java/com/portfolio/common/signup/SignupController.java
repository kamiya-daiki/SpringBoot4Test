package com.portfolio.common.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
public class SignupController {

    public final SignupUserService signupUserService;
    private static final Logger log =
        LoggerFactory.getLogger(SignupController.class);

    @PostMapping("/api/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password
    ) {
        try
        {
            log.info("SignupController: signup called start : username=" + username);
            signupUserService.createUser(username, password);
            log.info("SignupController: signup called end : username=" + username);
        } 
        catch (Exception e) 
        {
            log.info(e.getMessage());
            return "/signup?error_signup=Error_creating_user";
        }

        return "/signin";
    }
}