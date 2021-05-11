package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class InvoiceControllerTest {

    @Autowired
    MockMvc mvc;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberInvoicesTest() throws Exception {

        String url = "/admin/invoices/number";
        mvc.perform(get(url).with(user("admin").password("admin")
                                               .roles("ADMIN")))
           .andExpect(model().attribute("numberInvoices", is(equalTo(9L))))
           .andExpect(status().isOk())
        ;
    }

    @ParameterizedTest
    @CsvSource({
            "S00001, C00001, 2",      // transferor -> cedente, fornitore
            "S00001, C00002, 3",      // transferee -> cessionario, cliente
            "S00002, C00003, 0"}
    )
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    void getAllInvoicesByTransferorCodeAndTransfereeCodeByPageTest(String transferorCode,
                                                                   String transfereeCode,
                                                                   int numberDocuments) throws Exception {

        String url = "/admin/invoices";
        mvc.perform(get(url).param("transferorCode", transferorCode)
                            .param("transfereeCode", transfereeCode)
                            .with(user("admin").password("admin")
                                               .roles("ADMIN")))
           .andExpect(model().attribute("customer", hasProperty("username", equalTo("admin"))))
           .andExpect(model().attribute("invoices", hasProperty("content", hasSize(numberDocuments))))
           .andExpect(status().isOk())
        ;
    }

    @ParameterizedTest
    @CsvSource({
            "S00001, 9",
            "S00002, 0",
            "S00003, 0"}
    )
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    void getAllInvoicesByTransferorCodeByPageTest(String transferorCode, int numberDocuments) throws Exception {

        String url = "/admin/invoices";
        String transfereeCode = "all";
        mvc.perform(get(url).param("transferorCode", transferorCode)
                            .param("transfereeCode", transfereeCode)
                            .with(user("admin").password("admin")
                                               .roles("ADMIN")))
           .andExpect(model().attribute("customer", hasProperty("username", equalTo("admin"))))
           .andExpect(model().attribute("invoices", hasProperty("content", hasSize(numberDocuments))))
           .andExpect(view().name("invoices/invoicesList"))
           .andExpect(status().isOk())
        ;
    }

    @ParameterizedTest
    @CsvSource({
            "C00001, 2",      // transferee -> cessionario, cliente
            "C00002, 3",      // transferor -> cedente, fornitore
            "C00003, 1",
            "C00004, 3"}
    )
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    void getAllInvoicesByTransfereeCodeByPageTest(String transfereeCode, int numberDocuments) throws Exception {

        String url = "/admin/invoices";
        String transferorCode = "all";
        mvc.perform(get(url).param("transferorCode", transferorCode)
                            .param("transfereeCode", transfereeCode)
                            .with(user("admin").password("admin")
                                               .roles("ADMIN")))
           .andExpect(model().attribute("customer", hasProperty("username", equalTo("admin"))))
           .andExpect(model().attribute("invoices", hasProperty("content", hasSize(numberDocuments))))
           .andExpect(status().isOk())
        ;
    }

    @Test
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    void getAllInvoicesByPageTest() throws Exception {
        // "/{transferorCode}/{transfereeCode}"
        String url = "/admin/invoices";
        mvc.perform(get(url).param("transferorCode", "all")
                            .param("transfereeCode", "all")
                            .with(user("admin").password("admin")
                                               .roles("ADMIN")))
           .andExpect(model().attribute("customer", hasProperty("username", equalTo("admin"))))
           .andExpect(model().attribute("invoices", hasProperty("content", hasSize(9))))
           .andExpect(status().isOk())
        ;
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllInvoicesHome() throws Exception {

        String url = "/admin/invoices/all/home";

        mvc.perform(get(url).with(user("admin").password("admin")
                                               .roles("ADMIN")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(model().attribute("customer", hasProperty("username", equalTo("admin"))))
           .andExpect(view().name("invoices/invoicesHome"))
           .andExpect(status().isOk())
        ;
    }
}