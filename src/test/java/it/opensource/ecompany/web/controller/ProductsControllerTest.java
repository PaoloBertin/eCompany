package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
public class ProductsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewAllProductsPage1Test() throws Exception {

        mvc.perform(get("/products").param("page", "0")
                                    .param("size", "10"))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           .andExpect(model().attributeExists("cartBean"))
           .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("catalog/productsList"))
           .andExpect(status().isOk());
    }

    @Disabled
    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewAllProductsPage3Test() throws Exception {

        mvc.perform(get("/products").param("page", "2")
                                    .param("size", "10"))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           .andExpect(model().attributeExists("cartBean"))
           .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("catalog/productsList"))
           .andExpect(status().isOk());
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewProducstByCategoryByPage1Test() throws Exception {

        mvc.perform(get("/products/{categoryId}/all", 1L).param("page", "0")
                                                         .param("size", "10"))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           .andExpect(model().attributeExists("cartBean"))
           .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("catalog/productsList"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewProducstByCategoryByPage2Test() throws Exception {

        mvc.perform(get("/products/{categoryId}/all", 1L).param("page", "1")
                                                         .param("size", "10"))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           .andExpect(model().attributeExists("cartBean"))
           .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("catalog/productsList"))
           .andExpect(status().isOk());
    }
}
