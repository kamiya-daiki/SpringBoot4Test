package com.portfolio.task;

import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @Column(name = "last_login_datetime")
    private Instant lastLoginDatetime;

    public Long getId() {
        return id;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Instant getCreateDatetime() {
        return createDatetime;
    }

    public Instant getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setCreateDatetime(Instant createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void setLastLoginDatetime(Instant lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }
}
