package com.portfolio.user.delete;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import com.portfolio.user.UserRepository;

@Service
public class DeleteUserService {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(Long id) {
        
        userRepository.deleteById(id);

        return ResponseEntity.ok("User deleted");
    }
}
