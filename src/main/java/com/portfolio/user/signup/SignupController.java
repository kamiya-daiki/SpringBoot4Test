package com.portfolio.user.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    private final SignupUserService signupUserService;

    public SignupController(SignupUserService signupUserService) {
        this.signupUserService = signupUserService;
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String email,
            @RequestParam String password
    ) {
        try
        {
            signupUserService.createUser(email, password);
        } 
        catch (Exception e) 
        {
            // return "/index";
            return "/index?message_signup=Error_creating_user";
        }

        return "/index";
    }
}
