package it.opensource.ecompany.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class WarehouseResourceTest {

    @Autowired
    private WarehouseService warehouseService;

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void viewAllWarehouseByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouses/all").with(user("admin").password("admin")
                                                                 .roles("ADMIN"))
                                              .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$", hasSize(5)))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouses/{warehouseId}", 1L).with(user("admin").password("admin")
                                                                               .roles("ADMIN"))
                                                            .contentType(MediaType.APPLICATION_JSON))
           //.andDo(print())
           .andExpect(jsonPath("$.name", equalTo("magazzino01")))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseByNameTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouses?name=magazzino01").with(user("admin").password("admin")
                                                                              .roles("ADMIN"))
                                                           .contentType(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(jsonPath("$.id", equalTo(1)))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void createWarehouseTest(@Autowired MockMvc mvc) throws Exception {

        Warehouse warehouse = new Warehouse();
        warehouse.setName("magazzino10");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(warehouse);

        mvc.perform(post("/api/warehouses").with(user("admin").password("admin")
                                                              .roles("ADMIN"))
                                           .contentType(MediaType.APPLICATION_JSON)
                                           .content(requestJson))
           .andExpect(status().is(201));

        int expected = 6;
        int actual = warehouseService.getAllWarehouse()
                                     .size();
        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void updateWarehouseTest(@Autowired MockMvc mvc) throws Exception {

        Warehouse warehouse = warehouseService.getWarehouseById(5L)
                                              .get();
        warehouse.setName("magazzino20");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(warehouse);

        mvc.perform(put("/api/warehouses").with(user("admin").password("admin")
                                                             .roles("ADMIN"))
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .content(requestJson))
           .andExpect(status().is2xxSuccessful());

        Long expected1 = 5L;
        Long actual1 = warehouseService.getWarehouseByName("magazzino20")
                                      .get()
                                      .getId();
        assertEquals(expected1, actual1);

        int expected2 = 5;
        int actual2 = warehouseService.getAllWarehouse()
                                     .size();
        assertEquals(expected2, actual2);

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void deleteWarehouseTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(delete("/api/warehouses/{warehouseId}", 5L).with(user("admin").password("admin")
                                                                                  .roles("ADMIN"))
                                                               .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().is(204));
    }

}
