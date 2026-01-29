package com.portfolio.user;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query("UPDATE users u SET u.last_login_datetime = :dt WHERE u.username = :username")
    void updateLastLoginDatetime(
        @Param("username") String username,
        @Param("dt") LocalDateTime dt);
}