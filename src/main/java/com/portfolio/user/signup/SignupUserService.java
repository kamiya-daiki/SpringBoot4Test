package com.portfolio.user.signup;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.portfolio.user.UserEntity;
import com.portfolio.user.UserRepository;

@Service
public class SignupUserService {

    private final UserRepository userRepository;

    public SignupUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup-process")
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password) {
        
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        this.userRepository.save(user);

        return ResponseEntity.ok("User created");
    }
}
