package it.opensource.ecompany.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.opensource.ecompany.domain.*;
import it.opensource.ecompany.domain.util.Causal;
import it.opensource.ecompany.domain.util.Document;
import it.opensource.ecompany.service.*;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dbh2")
@AutoConfigureMockMvc
@SpringBootTest
class WarehouseJournalResourceTest {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private LineItemWarehouseJournalService lineItemWarehouseJournalService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private DocumentationWarehouseJournalService documentationWarehouseJournalService;

    @Autowired
    private WarehouseJournalService warehouseJournalService;

    private static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseJournalByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseJournal/all/{warehousejournalId}", 5).with(user("admin").password("admin")
                                                                                               .roles("ADMIN"))
                                                                            .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.documentationWarehouseJournal.id", equalTo(5)))
           .andExpect(status().isOk());
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseJournalByWarehouseIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseJournal/{warehouseId}/all", 4).with(user("admin").password("admin")
                                                                                        .roles("ADMIN"))
                                                                     .contentType(MediaType.APPLICATION_JSON))
           //   .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(4)))
           .andExpect(jsonPath("$.totalElements", equalTo(35)))
           .andExpect(status().isOk());
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseJournalByWarehouseNameTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseJournal/all/all").param("warehouseName", "magazzino04")
                                                        .with(user("admin").password("admin")
                                                                           .roles("ADMIN"))
                                                        .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(4)))
           .andExpect(jsonPath("$.totalElements", equalTo(35)))
           .andExpect(status().isOk());
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseJournmalBetweenDateTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseJournal/{warehouseId}/all", 4).param("dateStart", "2018-10-01")
                                                                     .param("dateEnd", "2018-10-31")
                                                                     .with(user("admin").password("admin")
                                                                                        .roles("ADMIN"))
                                                                     .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(4)))
           .andExpect(jsonPath("$.totalElements", equalTo(35)))
           .andExpect(status().isOk());

    }

    // @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getWarehouseJournmalDateTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/warehouseJournal/{warehouseId}/all", 4).param("dateStart", "2018-10-01")
                                                                     .param("dateEnd", "2018-10-31")
                                                                     .with(user("admin").password("admin")
                                                                                        .roles("ADMIN"))
                                                                     .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.totalPages", equalTo(4)))
           .andExpect(jsonPath("$.totalElements", equalTo(35)))
           .andExpect(status().isOk());

    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void createWarehouseJournalTest(@Autowired MockMvc mvc) throws Exception {

        Warehouse warehouse = warehouseService.getWarehouseById(4L)
                                              .get();
        Product product = productsService.getProductById(1L);
        LineItemWarehouseJournal lineItemWarehouseJournal = lineItemWarehouseJournalService.getLineItemWarehouseJournalById(1L)
                                           .get(); // TODO sostituire con create

        DocumentationWarehouseJournal documentationWarehouseJournal = new DocumentationWarehouseJournal();
        documentationWarehouseJournal.setId(1000L);
        documentationWarehouseJournal.setWarehouse(warehouse);
        documentationWarehouseJournal.setCausal(Causal.PURCHASE);
        documentationWarehouseJournal.setDocument(Document.TRANSPORT_DOCUMENT);
        // documentation.setDocumentDate(LocalDate.of(2018, 10, 15)); // TODO riattivare
        documentationWarehouseJournal.setDocumentNumber(100L);
        documentationWarehouseJournal.setLineItemWarehouseJournal(lineItemWarehouseJournal);

        WarehouseJournal warehouseJournal = new WarehouseJournal();
        warehouseJournal.setDocumentationWarehouseJournal(documentationWarehouseJournal);

        mvc.perform(post("/api/warehouseJournal").with(user("admin").password("admin")
                                                                    .roles("ADMIN"))
                                                 .accept(MediaType.APPLICATION_JSON)
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .content(asJsonString(warehouseJournal)))

           .andExpect(status().is(201));

        int expected = 85;
        int actual = warehouseJournalService.getAllWarehouseJurnal()
                                            .size();
        assertEquals(expected, actual);
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void updateWarehouseJournalTest(@Autowired MockMvc mvc) throws Exception {

        WarehouseJournal warehouseJournal = warehouseJournalService.getWarehouseJournalById(5L)
                                                                   .get();
        DocumentationWarehouseJournal documentationWarehouseJournal = warehouseJournal.getDocumentationWarehouseJournal();
        documentationWarehouseJournal.setDocument(Document.INVOICE);
        warehouseJournal.setDocumentationWarehouseJournal(documentationWarehouseJournal);

        mvc.perform(put("/api/warehouseJournal").with(user("admin").password("admin")
                                                                   .roles("ADMIN"))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(asJsonString(warehouseJournal)))
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

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void deleteWarehouseJournalTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(delete("/api/warehouseJournal/{warehouseJournalId}", 5L).with(user("admin").password("admin")
                                                                                               .roles("ADMIN"))
                                                                            .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().is(204));

        int expected = 99;
        int actual = warehouseJournalService.getAllWarehouseJurnal()
                                            .size();
        assertEquals(expected, actual);
    }

}