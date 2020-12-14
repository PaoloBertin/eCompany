package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ActiveProfiles("dbh2")
@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {

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