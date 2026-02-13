package com.portfolio.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository
        extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Query("DELETE TaskEntity t WHERE t.user_id = :user_id")
    int deleteTasksByUserId(@Param("user_id") Long user_id);
}
