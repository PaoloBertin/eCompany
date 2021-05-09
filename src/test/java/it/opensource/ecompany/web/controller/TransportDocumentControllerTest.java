package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TransportDocumentControllerTest {

    @Autowired
    MockMvc mvc;

/*
    @MockBean
    private TransportDocumentService mockRransportDocumentService;
*/

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllTransportDocumentByTransfereeCodeByPageTestUnit() throws Exception {

        mvc.perform(get("/admin/transportdocuments/{transfereeCode}", "C00001").with(user("admin").password("admin")
                                                                                        .roles("ADMIN")))
           .andExpect(model().attribute("transportDocuments", hasProperty("content", hasSize(6))))
           .andExpect(status().isOk())
        ;
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getNumberTransportDocumentByTransfereeCodeByPageTest() throws Exception {

        mvc.perform(get("/admin/transportdocuments/number").with(user("admin").password("admin")
                                                                              .roles("ADMIN")))
           .andExpect(model().attribute("numberDocuments", is(equalTo(6L))))
           .andExpect(status().isOk())
        ;
    }
}
