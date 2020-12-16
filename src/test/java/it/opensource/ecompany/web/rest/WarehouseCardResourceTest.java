package it.opensource.ecompany.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.opensource.ecompany.service.LineItemWarehouseService;
import it.opensource.ecompany.service.WarehouseCardService;
import it.opensource.ecompany.service.WarehouseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dbh2")
@AutoConfigureMockMvc
@SpringBootTest
class WarehouseCardResourceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseCardService warehouseCardService;

    @Autowired
    private LineItemWarehouseService lineItemWarehouseCardService;

    private static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberWarehouseCardTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard").param("numberWarehouseCard", "true")
                                             .with(user("admin").password("admin")
                                                                .roles("ADMIN"))
                                             .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.number", equalTo(84)));

    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberWarehouseCardsBydWarehouseIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/{warehouseId}", 1L).param("numberWarehouseCardByWarehouse", "true")
                                                               .with(user("admin").password("admin")
                                                                                  .roles("ADMIN"))
                                                               .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.number", equalTo(25)));

    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberWarehouseCardsByWarehouseIdAndLineItemProductIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/{warehouseId}/{productId}", 1L, 1L).param("numberByWarehouseAndProductId", "true")
                                                                               .with(user("admin").password("admin")
                                                                                                  .roles("ADMIN"))
                                                                               .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.number", equalTo(25)));

    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardByIdSuccessTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/all/{warehouseCardId}", 1L).with(user("admin").password("admin")
                                                                                          .roles("ADMIN"))
                                                                       .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.inventoryValue", equalTo(400.0)));
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardByIdFailureTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/all/{warehouseCardId}", 100L).with(user("admin").password("admin")
                                                                                            .roles("ADMIN"))
                                                                         .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isNotFound());
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardByWarehouseTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/{warehouseId}/all", 4).with(user("admin").password("admin")
                                                                                     .roles("ADMIN"))
                                                                  .contentType(MediaType.APPLICATION_JSON))
           //   .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(2)))
           .andExpect(jsonPath("$.totalElements", equalTo(19)))
           .andExpect(status().isOk());
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardByWarehouseAndProductIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/{warehouseId}/all/{productId}", 4L, 1L).param("warehouseName", "magazzino04")
                                                                                   .with(user("admin").password("admin")
                                                                                                      .roles("ADMIN"))
                                                                                   .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(2)))
           .andExpect(jsonPath("$.totalElements", equalTo(19)))
           .andExpect(status().isOk());
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardBetweenDateTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/{warehouseId}/all", 4).param("dateStart", "2018-10-01")
                                                                  .param("dateEnd", "2018-10-31")
                                                                  .with(user("admin").password("admin")
                                                                                     .roles("ADMIN"))
                                                                  .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(2)))
           .andExpect(jsonPath("$.totalElements", equalTo(19)))
           .andExpect(status().isOk());

    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseCardDateTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseCard/{warehouseId}/all", 4).param("dateStart", "2018-10-01")
                                                                  .param("dateEnd", "2018-10-31")
                                                                  .with(user("admin").password("admin")
                                                                                     .roles("ADMIN"))
                                                                  .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(1)))
           .andExpect(jsonPath("$.totalElements", equalTo(4)))
           .andExpect(status().isOk());

    }
/*
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void createWarehouseCardTest(@Autowired MockMvc mvc) throws Exception {

        Warehouse warehouse = warehouseService.getWarehouseById(4L)
                                              .get();
        LineItem lineItem = lineItemService.getLineItemById(1L)
                                           .get();

        WarehouseCard warehouseCard = new WarehouseCard();
        warehouseCard.setWarehouse(warehouse);
        warehouseCard.setCausal(Causal.PURCHASE);
        warehouseCard.setDocument(Document.TRANSPORT_DOCUMENT);
        //  warehouseCard.setDocumentDate(LocalDate.of(2018, 10, 15));
        warehouseCard.setDocumentNumber(100L);
        warehouseCard.setLineItem(lineItem);

        mvc.perform(post("/api/warehouseCard").with(user("admin").password("admin")
                                                                 .roles("ADMIN"))
                                              .accept(MediaType.APPLICATION_JSON)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(asJsonString(warehouseCard)))

           .andExpect(status().is(201));

        int expected = 85;
        int actual = warehouseCardService.getAllWarehouseCard()
                                         .size();
        assertEquals(expected, actual);
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void updateWarehouseCardTest(@Autowired MockMvc mvc) throws Exception {

        WarehouseCard warehouseCard = warehouseCardService.getWarehouseCardById(5L)
                                                          .get();
        warehouseCard.setDocument(Document.INVOICE);

        mvc.perform(put("/api/warehouseCard").with(user("admin").password("admin")
                                                                .roles("ADMIN"))
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content(asJsonString(warehouseCard)))
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

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void deleteWarehouseCardTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(delete("/api/warehouseCard/{warehouseCardId}", 5L).with(user("admin").password("admin")
                                                                                         .roles("ADMIN"))
                                                                      .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().is(204));

        int expected = 83;
        int actual = warehouseCardService.getAllWarehouseCard()
                                         .size();
        assertEquals(expected, actual);
    }
*/
}