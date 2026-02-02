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
        signupUserService.createUser(username, password);

        return "/index";
    }
}
