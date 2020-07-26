package it.opensource.ecompany.web.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// ActiveProfiles("html")
@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void viewAllProductsPage1Test() throws Exception {

        mvc
            .perform(get("/products")
                         .param("page", "0")
                         .param("size", "10")
                    )
            .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
            .andExpect(view().name("catalog/list"))
            .andExpect(status().isOk());
    }

    @Test
    public void viewAllProductsPage3Test() throws Exception {

        mvc
            .perform(get("/products")
                         .param("page", "2")
                         .param("size", "10")
                    )
            .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
            .andExpect(view().name("catalog/list"))
            .andExpect(status().isOk());
    }

    @Test
    public void viewProducstByCategoryByPage1Test() throws Exception {

        mvc
            .perform(get("/products/{categoryId}", 1L)
                         .param("page", "0")
                         .param("size", "10")
                    )
            .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
            .andExpect(view().name("catalog/list"))
            .andExpect(status().isOk());
    }

    @Test
    public void viewProducstByCategoryByPage2Test() throws Exception {

        mvc
            .perform(get("/products/{categoryId}", 1L)
                         .param("page", "1")
                         .param("size", "10")
                    )
            .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalCost", closeTo(3.0, 0.001))))
            .andExpect(view().name("catalog/list"))
            .andExpect(status().isOk());

    }
}
