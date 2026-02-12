package com.portfolio.task;

import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository
        extends JpaRepository<TaskEntity, Long> {

    Optional<TaskEntity> findByEmail(String email);
    Optional<TaskEntity> deleteById(long id);

    @Modifying
    @Query("UPDATE UserEntity u SET u.lastLoginDatetime = :dt WHERE u.email = :email")
    int updateLastLoginDatetime(
        @Param("email") String email,
        @Param("dt") Instant dt
    );
}
