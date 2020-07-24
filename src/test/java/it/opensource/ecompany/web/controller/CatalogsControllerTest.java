package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class CatalogsControllerTest {

    @Test
    public void getAllCategoriesAdmin(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/catalog"))
           .andExpect(model().attribute("categories", hasSize(6)))
           .andExpect(view().name("catalog/categoriesListAdmin"))
           .andExpect(status().isOk());
    }

    @Test
    public void createCategoryForm(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/catalog").param("form", "")
                                         .with(user("admin.ecompany").password("admin")
                                                                     .roles("ADMIN")))
           .andExpect(view().name("catalog/editCategory"))
           .andExpect(status().isOk());


    }

    @Test
    public void create() {

    }
}