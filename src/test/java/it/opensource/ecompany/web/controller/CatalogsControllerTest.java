package it.opensource.ecompany.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class CatalogsControllerTest {

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getAllCategoriesAdmin(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/catalog/all").with(user("admin.ecompany").password("admin")
                                                                         .roles("ADMIN")))
           .andExpect(model().attribute("categories", hasSize(6)))
           .andExpect(view().name("catalog/categoriesListAdmin"))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void createCategoryForm(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/catalog").param("form", "")
                                         .with(user("admin.ecompany").password("admin")
                                                                     .roles("ADMIN")))
           .andExpect(view().name("catalog/editCategory"))
           .andExpect(status().isOk());

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void createCategorySuccessTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/admin/catalog").param("form", "")
                                          .param("name", "eBook")
                                          .with(user("admin.ecompany").password("admin")
                                                                      .roles("ADMIN")))
           .andExpect(MockMvcResultMatchers.flash()
                                           .attribute("message", hasProperty("type", equalTo("success"))))
           .andExpect(redirectedUrl("/admin/catalog/all"));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void createCategoryErrorTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/admin/catalog").param("form", "")
                                          .with(user("admin.ecompany").password("admin")
                                                                      .roles("ADMIN")))
           .andExpect(model().attribute("message", hasProperty("type", equalTo("error"))))
           .andExpect(view().name("catalog/editCategory"))
           .andExpect(status().isOk());
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void deleteCategoryTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(delete("/admin/catalog/{categoryId}", 6L).with(user("admin.ecompany").password("admin")
                                                                                         .roles("ADMIN")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(5)))
           .andExpect(status().isOk());
    }
}
