package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogsControllerTest {

    @Test
    public void getAllCategoriesAdmin(@Autowired MockMvc mvc) throws Exception{
        mvc
            .perform(get("/catalog"))
            .andExpect(model().attribute("categories", hasSize(6)))
            .andExpect(view().name("catalog/categoriesListAdmin"))
            .andExpect(status().isOk());
    }

    @Test
    public void createCategoryForm() {

    }

    @Test
    public void create() {

    }
}