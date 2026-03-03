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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SignupUserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(SignupUserService.class);
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignupUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(
        @RequestParam String username, 
        @RequestParam String password
    ){
        
        //System.out.println("SignupUserService: createUser called start : username=" + username);
        log.info("SignupUserService: createUser called start : username=" + username);
        UserEntity user = new UserEntity();
        user.setUser_id(username);
        user.setEmail(username);
        user.setUser_password(passwordEncoder.encode(password));
        user.setCreateDatetime(Instant.now());
        user.setEnabled(true);
        user.setAuthority(0);
        user.setScreen_mode(0);
        
        this.userRepository.save(user);
        //System.out.println("SignupUserService: createUser called end : username=" + username);
        log.info("SignupUserService: createUser called end : username=" + username);

        return ResponseEntity.ok("User created");
    }

    @PostMapping("/api/signup")
    public ResponseEntity<String> createUserApi(
        @RequestParam String username,
        @RequestParam String password
    ) throws Exception{
        
        System.out.println("SignupUserServiceApi: createUser called start : username=" + username);
        UserEntity user = new UserEntity();
        user.setEmail(username);
        user.setUser_password(passwordEncoder.encode(password));
        user.setCreateDatetime(Instant.now());
        user.setEnabled(true);
        
        this.userRepository.save(user);
        System.out.println("SignupUserServiceApi: createUser called end : username=" + username);

        return ResponseEntity.ok("User created");
    }
}
