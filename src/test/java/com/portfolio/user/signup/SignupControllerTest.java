package com.portfolio.user.signup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // mvn test -Dtest=SignupControllerTest#SignupTest0001
    @Test
    void SignupTest0001() throws Exception {
        mockMvc.perform(post("/signup")
				.param("username", "SignupTest0001")
				.param("password", "password"))
				.andExpect(redirectedUrl("/index"))
				.andExpect(status().isOk());
        // 結果はDBを目視で確認
        // SELECT * FROM users WHERE username = 'SignupTest0001';
    }
    
    // mvn test -Dtest=SignupControllerTest#SignupTest0002
    @Test
    void SignupTest0002() throws Exception {
        mockMvc.perform(get("/signup")
				.param("username", "SignupTest0002")
				.param("password", "password"))
				.andExpect(redirectedUrl("/index"))
				.andExpect(status().isOk());
        // 結果はDBを目視で確認
        // SELECT * FROM users WHERE username = 'SignupTest0002';
    }
}
