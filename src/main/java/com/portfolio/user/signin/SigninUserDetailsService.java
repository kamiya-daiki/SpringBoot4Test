package com.portfolio.user.signin;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.portfolio.user.UserEntity;
import com.portfolio.user.UserRepository;

@Service
public class SigninUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger log =
        LoggerFactory.getLogger(UserRepository.class);

    public SigninUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        log.info("User: {"+ username +"} check");

        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User or Password disabled"));

        log.info("User: {"+ username +"} found");

        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new DisabledException("User or Password disabled");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public void updateLastLogin(String username, Instant lastLogin) {

        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

        if (user != null) {
            user.setLastLoginDatetime(lastLogin);
            userRepository.save(user);
        }
    }
}
