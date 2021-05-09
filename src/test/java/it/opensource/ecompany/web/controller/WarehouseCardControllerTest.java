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

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasProperty;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class WarehouseCardControllerTest {

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
    void getWarehouseCardsHome() throws Exception {

        mvc.perform(get("/admin/warehouseCards/all/home").with(user("admin").password("admin")
                                                                            .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouses", hasProperty("content", hasSize(8))))
           .andExpect(view().name("warehousecards/warehouseCardsHome"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseCardByPageTest() throws Exception {

        mvc.perform(get("/admin/warehouseCards/all").param("page", "0")
                                                    .param("size", "10")
                                                    .with(user("admin").password("admin")
                                                                       .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouseCards", hasProperty("content", hasSize(10))))
           .andExpect(view().name("warehousecards/warehouseCardsList"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardsByWarehouseIdTest() throws Exception {

        mvc.perform(get("/admin/warehouseCards/{warehouseId}", 1L).with(user("admin").password("admin")
                                                                                     .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouseCards", hasProperty("content", hasSize(10))))
           .andExpect(view().name("warehousecards/warehouseCardsListByWarehouse"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardsByWarehouseIdAndProductCodeTest() throws Exception {

        mvc.perform(get("/admin/warehouseCards/{warehouseId}/{productCode}", 1L, "8883780450").with(user("admin").password("admin")
                                                                                                                 .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouseCards", hasProperty("content", hasSize(2))))
           .andExpect(view().name("warehousecards/warehouseCardsListByWarehouseAndProductCode"))
           .andExpect(status().isOk());
    }
}