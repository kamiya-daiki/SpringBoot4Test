package com.portfolio.web.user.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/signin"})
    public String signin() {
        return "/signin";
    }
}
