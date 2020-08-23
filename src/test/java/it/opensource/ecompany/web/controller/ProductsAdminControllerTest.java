package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class ProductsAdminControllerTest {

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewAllProductsByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/products/all/all").param("page", "0")
                                                  .param("size", "10")
                                                  .with(user("admin").password("admin")
                                                                     .roles("ADMIN")))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("catalog/productsListAdmin"))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewProducstByCategoryByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/products/{categoryId}/all", 2L).param("page", "0")
                                                               .param("size", "10")
                                                               .with(user("admin").password("admin")
                                                                                  .roles("ADMIN")))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("catalog/productsListAdmin"))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewProductTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/products/{productId}", 2L).param("page", "0")
                                                          .param("size", "10")
                                                          .with(user("admin").password("admin")
                                                                             .roles("ADMIN")))
           .andExpect(model().attribute("product", hasProperty("name", equalTo("Resurrection"))))
           .andExpect(view().name("catalog/productShow"))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void downloadPhotoTest() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void searchProductTest() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void createProductTest() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void updateProductTest() {

    }
}
