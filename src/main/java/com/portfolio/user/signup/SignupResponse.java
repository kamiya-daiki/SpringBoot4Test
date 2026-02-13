package com.portfolio.user.signup;

import org.springframework.data.annotation.Id;
import com.portfolio.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    public static SignupResponse from(UserEntity entity) {
        return SignupResponse.builder()
                .user_id(entity.getUser_id())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
