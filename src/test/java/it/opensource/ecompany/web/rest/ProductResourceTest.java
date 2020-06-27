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
    void getAllProductsByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/all").param("page", "0")
                                            .param("size", "10")
                                            .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.length()", equalTo(10)))
           .andExpect(status().isOk());
    }

    @Test
    void getProductsByCategory1ByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 1).param("page", "0")
                                                        .param("size", "10")
                                                        .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.length()", equalTo(10)))
           .andExpect(status().isOk());
    }

    @Test
    void getProductsByCategory6ByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 6).param("page", "0")
                                                        .param("size", "10")
                                                        .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.length()", equalTo(2)))
           .andExpect(status().isOk());
    }

    @Test
    public void getProductByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/all/{productId}", 1L))
           .andExpect(jsonPath("$.name", equalTo("Da Visual Basic a Java")))
           .andExpect(status().isOk());
    }

    @Test
    void getPhotoByProductIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/products/photo/{productid}", 1L)).andDo(print()).andExpect(status().isOk());
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
