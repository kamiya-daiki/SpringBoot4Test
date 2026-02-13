package com.portfolio.user.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SignupControllerApi {

    private final SignupUserService signupUserService;

    @PostMapping("/api/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password
    ) {
        try
        {
            System.out.println("SignupControllerApi: signup called start : username=" + username);
            signupUserService.createUser(username, password);
            System.out.println("SignupControllerApi: signup called end : username=" + username);
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            return "/index?error_signup=Error_creating_user";
        }

        return "/index";
    }
}