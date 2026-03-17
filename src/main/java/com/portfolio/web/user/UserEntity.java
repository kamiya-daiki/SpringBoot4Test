package com.portfolio.web.user;

import java.io.Serializable;
import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String user_id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String user_password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @Column(name = "last_update_password_datetime")
    private Instant lastUpdatePasswordDatetime;

    @Column(name = "last_login_datetime")
    private Instant lastLoginDatetime;

    @Column(nullable = false)
    private int authority;

    @Column(nullable = false)
    private int screen_mode;
}
