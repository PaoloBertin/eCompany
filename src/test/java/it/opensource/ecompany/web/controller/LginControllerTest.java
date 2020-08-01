package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@AutoConfigureMockMvc
@SpringBootTest
public class LginControllerTest {

    @Test
    public void loginSuccessTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/login").param("form", "")
                                  .param("username", "admin.ecompany")
                                  .param("password", "admin"))
           .andExpect(redirectedUrl("/default"));
    }

    @Test
    public void loginFailureTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/login").param("form", "")
                                  .param("username", "admin.ecompany")
                                  .param("password", "user"))
        .andExpect(redirectedUrl("/login?error=true"));
    }
}
