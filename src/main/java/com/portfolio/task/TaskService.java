package com.portfolio.task;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class TaskService {

    private final TaskRepository userRepository;

    public TaskService(TaskRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteUserById(String email) {

        TaskEntity user = userRepository
            .findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.deleteById(user.getId());
    }

    @Transactional
    public void deleteUserByEmail(String email) {

        TaskEntity user = userRepository
            .findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.delete(user);
    }
}
