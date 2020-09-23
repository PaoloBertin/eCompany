package it.opensource.ecompany.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest
class SalesOrdersControllerTest {

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllSalesOrders(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/salesorders/all").with(user("admin").password("admin")
                                                                    .roles("ADMIN")))
           .andExpect(model().attribute("salesOrders", IsCollectionWithSize.hasSize(10)));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getPurchaseOrderById(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/salesorders/{saleorderId}", 1L).with(user("admin").password("admin")
                                                                                  .roles("ADMIN")))
           .andExpect(model().attribute("saleOrder", hasProperty("totalAmount", equalTo(169.5))));
    }
}