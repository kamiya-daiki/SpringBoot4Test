package com.portfolio.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository
        extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Query("DELETE TaskEntity t WHERE t.user_id = :user_id")
    int deleteTasksByUserId(@Param("user_id") Long user_id);
}
