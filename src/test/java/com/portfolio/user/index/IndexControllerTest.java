package com.portfolio.user.index;

import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void IndexTest0001() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk());
    }
}
