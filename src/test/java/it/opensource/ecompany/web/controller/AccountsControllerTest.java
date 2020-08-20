package it.opensource.ecompany.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class AccountsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewAccountsTest(@Autowired MockMvc mvc) throws Exception {

        BigDecimal balance = new BigDecimal(10000);
        BigDecimal error = new BigDecimal(0.00001);

        mvc.perform(get("/admin/accounts").with(user("admin").password("admin")
                                                           .roles("ADMIN")))
           .andExpect(model().attribute("accounts", IsCollectionWithSize.hasSize(1)))
           .andExpect(model().attribute("accounts", hasItem(hasProperty("balance", is(closeTo(balance, error))))))
           .andExpect(view().name("accounts/accountsList"))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewAccount(@Autowired MockMvc mvc) throws Exception {

        BigDecimal balance = new BigDecimal(10000);
        BigDecimal error = new BigDecimal(0.00001);

        mvc.perform(get("/admin/accounts/{accountId}", 1L).with(user("admin").password("admin")
                                                               .roles("ADMIN")))
           .andExpect(model().attribute("account", hasProperty("balance", is(closeTo(balance, error)))))
           .andExpect(view().name("accounts/accountShow"))
           .andExpect(status().isOk());
    }
}