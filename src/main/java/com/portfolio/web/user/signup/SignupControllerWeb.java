package com.portfolio.web.user.signup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;

import com.portfolio.common.signup.SignupUserService;

@Controller
@RequiredArgsConstructor
public class SignupControllerWeb {

    private final SignupUserService signupUserService;
    private static final Logger log =
        LoggerFactory.getLogger(SignupControllerWeb.class);

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
