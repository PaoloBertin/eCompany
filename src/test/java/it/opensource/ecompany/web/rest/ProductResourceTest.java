package it.opensource.ecompany.web.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;;

@AutoConfigureMockMvc
@SpringBootTest
class ProductResourceTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllProductsByPage(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/all").param("page", "0").param("size", "10").contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.content.length()", equalTo(10)))
           .andExpect(status().isOk());
    }

    @Test
    void getProducstByCategoryByPage(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 6).param("page", "0")
                                                    .param("size", "10")
                                                    .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.content.length()", equalTo(2)))
           .andExpect(status().isOk());
    }

    @Test
    void getProductsByCategoryByPage(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 6).param("page", "0")
                                                    .param("size", "10")
                                                    .contentType(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(jsonPath("$.content.length()", equalTo(2)))
           .andExpect(status().isOk());
    }

    @Test
    void downloadPhoto() {

    }

    @Test
    void create() {

    }

    @Test
    void update() {

    }

    @Test
    void updateForm() {

    }

    @Test
    void searchProduct() {

    }
}
