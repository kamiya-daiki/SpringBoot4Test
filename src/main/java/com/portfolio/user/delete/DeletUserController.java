package com.portfolio.user.delete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeletUserController {

    private final DeleteUserService deleteUserService;

    public DeletUserController(DeleteUserService deleteUserService) {
        this.deleteUserService = deleteUserService;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        deleteUserService.deleteUser(id);
        return "/home";
    }
}
