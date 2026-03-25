package com.portfolio.api.user.signup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.portfolio.common.signup.SignupUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupUserServiceApi {

    private final SignupUserService signupUserService;
    private static final Logger log = LoggerFactory.getLogger(SignupUserServiceApi.class);

    @PostMapping("/api/signup")
    public ResponseEntity<String> createUserApi(
        @RequestParam String username,
        @RequestParam String password
    ) throws Exception{
        
        log.info("SignupUserServiceApi: createUser called start : username=" + username);
        var result = signupUserService.createUser(username, password);
        log.info("SignupUserServiceApi: createUser called end : username=" + username, " result=" + result.getBody());

        return result;
    }
}
