package com.portfolio.user.signin;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.portfolio.user.UserEntity;
import com.portfolio.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new DisabledException("User disabled");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // BCrypt hash
                .roles("USER")
                .build();
    }
}
