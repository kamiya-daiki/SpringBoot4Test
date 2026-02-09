package com.portfolio.user.signup;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.portfolio.user.UserEntity;
import com.portfolio.user.UserRepository;

@Service
public class SignupUserService {

    private final UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignupUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password) throws Exception{
        
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreateDatetime(Instant.now());
        user.setEnabled(true);
        
        this.userRepository.save(user);

        return ResponseEntity.ok("User created");
    }
}
