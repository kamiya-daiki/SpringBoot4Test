package com.portfolio.user;

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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id_history;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String user_password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @Column(name = "last_login_datetime")
    private Instant lastLoginDatetime;

    @Column(nullable = false)
    private int authority;

    @Column(nullable = false)
    private int screen_mode;
    
    public int hashCode() {
		int hashCode = 0;
		if (user_id != null) {
			hashCode ^= user_id.hashCode();
		}
		if (user_id_history != null) {
			hashCode ^= user_id_history.hashCode();
		}
		return hashCode;
	}
}
