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
            signupUserService.createUser(username, password);
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("return : /index?message_signup=Error_creating_user");
            return "/index?message_signup=Error_creating_user";
        }

        return "/index";
    }
}
