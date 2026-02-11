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
            @RequestParam String username,
            @RequestParam String password
    ) {
        try
        {
            System.out.println("SignupController: signup called start : username=" + username);
            signupUserService.createUser(username, password);
            System.out.println("SignupController: signup called end : username=" + username);
        } 
        catch (Exception e) 
        {
            // return "/index";
            return "/index?error_signup=Error_creating_user";
        }

        return "/index";
    }
}
