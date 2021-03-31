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
class CustomerControllerTest {

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void signupTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/customers/registration").param("firstname", "Giuseppe")
                                                   .param("lastname", "Mazzini")
                                                   .param("email", "giuseppe.mazzini@dummy.com")
                                                   .param("username", "giuseppe.mazzini")
                                                   .param("password", "user"))
           .andExpect(redirectedUrl("/"));
    }

}