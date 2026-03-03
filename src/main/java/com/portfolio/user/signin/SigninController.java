package com.portfolio.user.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {

    @GetMapping("/signup")
    public String signup()
    {
        return "signup";
    }

    @GetMapping("/signin-action")
    public String signinAction()
    {
        return "signin-action";
    }
}
