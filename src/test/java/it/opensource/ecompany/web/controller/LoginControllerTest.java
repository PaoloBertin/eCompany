package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTest {

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void loginSuccessTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/login").param("form", "")
                                  .param("username", "admin")
                                  .param("password", "admin"))
           .andExpect(redirectedUrl("/default"));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void loginFailureTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/login").param("form", "")
                                  .param("username", "admin")
                                  .param("password", "user"))
        .andExpect(redirectedUrl("/login?error=true"));
    }
}
