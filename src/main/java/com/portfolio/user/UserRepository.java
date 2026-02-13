package com.portfolio.user;

import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> deleteById(long id);

    @Modifying
    @Query("UPDATE UserEntity u SET u.lastLoginDatetime = :dt WHERE u.email = :email")
    int updateLastLoginDatetime(
        @Param("email") String email,
        @Param("dt") Instant dt
    );

    @Modifying
    @Query("UPDATE UserEntity u SET u.lastLoginDatetime = :dt WHERE u.email = :email")
    int updateLastLoginDatetimeApi(
        @Param("email") String email,
        @Param("dt") Instant dt
    );
}
