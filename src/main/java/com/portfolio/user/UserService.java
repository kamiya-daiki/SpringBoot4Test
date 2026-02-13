package com.portfolio.user;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteUserById(String email) {

        UserEntity user = userRepository
            .findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.deleteById(user.getUser_id());
    }

    @Transactional
    public void deleteUserByEmail(String email) {

        UserEntity user = userRepository
            .findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.delete(user);
    }
}
