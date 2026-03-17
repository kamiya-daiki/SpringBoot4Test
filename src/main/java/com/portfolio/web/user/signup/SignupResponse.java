package com.portfolio.web.user.signup;

import com.portfolio.web.user.UserEntity;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupResponse {

    @Column(nullable = false, unique = true, length = 50)
    private String user_id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    public static SignupResponse from(UserEntity entity) {
        return SignupResponse.builder()
                .user_id(entity.getUser_id())
                .email(entity.getEmail())
                .password(entity.getUser_password())
                .build();
    }
}
