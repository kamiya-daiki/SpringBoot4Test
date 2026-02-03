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
    public void deleteUserById(String username) {

        UserEntity user = userRepository
            .findByUsername(username)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.deleteById(user.getId());
    }

    @Transactional
    public void deleteUserByName(String username) {

        UserEntity user = userRepository
            .findByUsername(username)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.delete(user);
    }
}
