package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@SpringBootTest
class SalesOrdersControllerTest {
    @Autowired
    private CartBean cartBean;

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
    void getAllSalesOrders(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/salesorders/all").with(user("admin").password("admin")
                                                                    .roles("ADMIN")))
           .andExpect(model().attribute("salesOrders", IsCollectionWithSize.hasSize(10)));
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getSalesOrderById(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/salesorders/{saleorderId}", 1L).with(user("admin").password("admin")
                                                                                  .roles("ADMIN")))
           .andExpect(model().attribute("saleOrder", hasProperty("totalAmount", equalTo(169.5))));
    }
}