package com.portfolio.task;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void deleteUserById(long user_id) {

        // TaskEntity task = taskRepository
        //     .deleteByUserId(user_id)
        //     .orElseThrow(() -> new IllegalStateException("User not found"));

        taskRepository.deleteTasksByUserId(user_id);
    }
}
