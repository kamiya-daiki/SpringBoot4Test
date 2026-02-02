package com.portfolio.user;

import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query("UPDATE UserEntity u SET u.lastLoginDatetime = :dt WHERE u.username = :username")
    int updateLastLoginDatetime(
        @Param("username") String username,
        @Param("dt") Instant dt
    );
}
