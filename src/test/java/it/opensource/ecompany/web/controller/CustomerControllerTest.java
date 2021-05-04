package it.opensource.ecompany.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void signupTest() throws Exception {

        mvc.perform(post("/customers/registration").param("firstname", "Giuseppe")
                                                   .param("lastname", "Mazzini")
                                                   .param("email", "giuseppe.mazzini@dummy.com")
                                                   .param("username", "giuseppe.mazzini")
                                                   .param("password", "user"))
           .andExpect(redirectedUrl("/"));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewAllCustomersTest() throws Exception{

        mvc.perform(get("/admin/customers/").with(user("admin").password("admin")
                                                               .roles("ADMIN")))
           .andExpect(model().attribute("customers", IsCollectionWithSize.hasSize(5)))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(status().isOk());
    }
}