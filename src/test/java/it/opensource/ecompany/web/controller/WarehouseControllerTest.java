package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.service.WarehouseService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class WarehouseControllerTest {

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

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                             .addFilters(springSecurityFilterChain)
                             .build();
    }

    @Autowired
    private WarehouseService warehouseService;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllWarehouseTest() throws Exception {

        mvc.perform(get("/admin/warehouses/all").with(user("admin").password("admin")
                                                                   .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouses", IsCollectionWithSize.hasSize(8)))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getWarehuseById() throws Exception {

        mvc.perform(get("/admin/warehouses/{warehouseId}", 1L).with(user("admin").password("admin")
                                                                                 .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("warehouse", hasProperty("name", equalTo("Antica Libreria di Bergamo"))))
           .andExpect(status().isOk());

    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getWarehuseByName() throws Exception {

        mvc.perform(get("/admin/warehouses?name=Antica Libreria di Bergamo").with(user("admin").password("admin")
                                                                                               .roles("ADMIN")))
           .andExpect(model().attribute("warehouse", hasProperty("id", equalTo(1L))))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void warehouseUpdateFormTest() throws Exception {

        mvc.perform(get("/admin/warehouses/{warehouseId}", 5L).param("form", "true")
                                                              .with(user("admin").password("admin")
                                                                                 .roles("ADMIN")))
           .andExpect(model().attribute("warehouse", hasProperty("name", equalTo("Antica Libreria di Lecco"))))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void warehouseUpdateTest() throws Exception {

        StringBuilder uri = new StringBuilder();
        uri.append(URLEncoder.encode("id", StandardCharsets.UTF_8.name()))
           .append('=')
           .append(URLEncoder.encode("5", StandardCharsets.UTF_8.name()))
           .append('&')
           .append(URLEncoder.encode("name", StandardCharsets.UTF_8.name()))
           .append('=')
           .append(URLEncoder.encode("Punto Vendita 05", StandardCharsets.UTF_8.name()))
           .append('&')
           .append(URLEncoder.encode("version", StandardCharsets.UTF_8.name()))
           .append('=')
           .append(URLEncoder.encode("0", StandardCharsets.UTF_8.name()));

        mvc.perform(put("/admin/warehouses").param("form", "true")
                                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                            .content(uri.toString())
                                            .with(user("admin").password("admin")
                                                               .roles("ADMIN")))
           // .andDo(print())
           .andExpect(redirectedUrl("/admin/warehouses/5"));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void createWarehouseFormTest() throws Exception {

        mvc.perform(get("/admin/warehouses").param("form", "true")
                                            .with(user("admin").password("admin")
                                                               .roles("ADMIN")))
           .andExpect(model().attribute("warehouse", hasProperty("id", is(nullValue()))))
           .andExpect(model().attribute("warehouse", hasProperty("name", is(nullValue()))))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void createWarehouseTest() throws Exception {

        StringBuilder uri = new StringBuilder();
        uri.append(URLEncoder.encode("name", StandardCharsets.UTF_8.name()))
           .append('=')
           .append(URLEncoder.encode("Punto Vendita 06", StandardCharsets.UTF_8.name()));

        mvc.perform(post("/admin/warehouses").param("form", "true")
                                             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                             .content(uri.toString())
                                             .with(user("admin").password("admin")
                                                                .roles("ADMIN")))
           .andDo(print())
           .andExpect(redirectedUrl("/admin/warehouses"));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void deleteWarehouse() throws Exception {

        mvc.perform(delete("/admin/warehouses/{warehouseId}", 5L).with(user("admin").password("admin")
                                                                                    .roles("ADMIN")))
           .andExpect(status().isOk());

        int expected = 7;
        int actual = warehouseService.getAllWarehouse()
                                     .size();
        assertEquals(expected, actual);
    }

}
