package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dbh2")
@AutoConfigureMockMvc
@SpringBootTest
class SuppliersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewAllSupplier() throws Exception {

        mvc.perform(get("/admin/suppliers").with(user("admin.ecompany").password("admin")
                                                                       .roles("ADMIN")))
           .andExpect(status().isOk());

    }
}