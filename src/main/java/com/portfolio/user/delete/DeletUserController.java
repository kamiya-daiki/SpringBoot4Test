package com.portfolio.user.delete;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.portfolio.user.UserService;

@Controller
public class DeletUserController {

    private final UserService userService;

    public DeletUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/delete")
    public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUserByEmail(userDetails.getUsername());
        return "redirect:/logout";
    }
}
