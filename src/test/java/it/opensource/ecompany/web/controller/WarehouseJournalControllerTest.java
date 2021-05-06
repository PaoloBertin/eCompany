package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class WarehouseJournalControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {

        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                  .addFilters(springSecurityFilterChain)
                                  .build();
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseJournalsHome() throws Exception {

        mvc.perform(get("/admin/warehousejournals/all/home").with(user("admin").password("admin")
                                                                               .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouses", hasProperty("content", hasSize(8))))
           .andExpect(view().name("warehousejournal/warehouseJournalsHome"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByPageTest() throws Exception {

        mvc.perform(get("/admin/warehousejournals/all").param("page", "0")
                                                       .param("size", "10")
                                                       .with(user("admin").password("admin")
                                                                          .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouseJournals", hasProperty("content", hasSize(10))))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseJournalsByWarehouseIdTest() throws Exception {

        mvc.perform(get("/admin/warehousejournals/{warehouseId}", 1L).with(user("admin").password("admin")
                                                                                        .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouseJournals", hasProperty("content", hasSize(10))))
           .andExpect(status().isOk());
    }
}