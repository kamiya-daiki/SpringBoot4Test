package com.portfolio.user.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final SignupUserService signupUserService;
    private static final Logger log =
        LoggerFactory.getLogger(SignupController.class);

    @PostMapping("/signup-action")
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
            return "/signup?error_signup=Error_creating_user";
        }

        return "/signin";
    }
}
