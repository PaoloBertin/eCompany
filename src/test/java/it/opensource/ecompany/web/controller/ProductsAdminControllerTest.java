package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class ProductsAdminControllerTest {

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void viewAllProductsByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/products/all/all").param("page", "0")
                                                  .param("size", "10")
                                                  .with(user("admin").password("admin")
                                                                     .roles("ADMIN")))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           // .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
           .andExpect(view().name("catalog/productsListAdmin"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void viewProducstByCategoryByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/products/{categoryId}/all", 2L).param("page", "0")
                                                               .param("size", "10")
                                                               .with(user("admin").password("admin")
                                                                                  .roles("ADMIN")))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", notNullValue()))
           .andExpect(view().name("catalog/productsListAdmin"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
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

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void downloadPhotoTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/products/{productId}", 2L).param("page", "0")
                                                          .param("size", "10")
                                                          .with(user("admin").password("admin")
                                                                             .roles("ADMIN")))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void searchProductTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/products/searchProduct").param("page", "0")
                                                        .param("size", "10")
                                                        .param("textToSearch", "Java")
                                                        .with(user("admin").password("admin")
                                                                           .roles("ADMIN")))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(5))))
           .andExpect(view().name("catalog/productsListAdmin"))
           .andExpect(status().isOk());
        ;
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void createProductSuccessTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/admin/products").param("page", "0")
                                           .param("size", "10")
                                           .param("form", "form")
                                           .param("categoryId", "1")
                                           .param("name", "Java 11")
                                           .param("description", "new version")
                                           .param("isbn", "8883780910")
                                           .param("price", "25.00")
                                           .param("categoryProduct", "Libri")
                                           .with(user("admin").password("admin")
                                                              .roles("ADMIN")))
           .andExpect(redirectedUrl("/admin/products/1/all"));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void createProductFailureTest(@Autowired MockMvc mvc) throws Exception {

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void updateProductTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/admin/products").param("page", "0")
                                           .param("size", "10")
                                           .param("form", "form")
                                           .param("productId", "1")
                                           .param("categoryId", "1")
                                           .param("categoryProduct", "Libri")
                                           .param("name", "Java 11")
                                           .param("isbn", "8883780561")
                                           .with(user("admin").password("admin")
                                                              .roles("ADMIN")))
           .andExpect(redirectedUrl("/admin/products/1/all"));

    }
}
