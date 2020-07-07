package it.opensource.ecompany.web.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    void getAllProductsByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/all").param("page", "0")
                                            .param("size", "10")
                                            .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.content.length()", equalTo(10)))
           .andExpect(status().isOk());
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    void getProductsByCategory1ByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 1).param("page", "0")
                                                        .param("size", "10")
                                                        .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.content.length()", equalTo(10)))
           .andExpect(status().isOk());
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    void getProductsByCategory6ByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 6).param("page", "0")
                                                        .param("size", "10")
                                                        .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.content.length()", equalTo(2)))
           .andExpect(status().isOk());
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    public void getProductByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/all/{productId}", 1L))
           .andExpect(jsonPath("$.name", equalTo("Da Visual Basic a Java")))
           .andExpect(status().isOk());
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    void getPhotoByProductIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/products/photo/{productid}", 1L)).andDo(print()).andExpect(status().isOk());
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    void createProductTest(@Autowired MockMvc mvc) throws Exception {

        Category category = new Category(1L, "Libri");
        Product product = new Product("aaaa", "bbbb", category);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(product);

        mvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON).content(requestJson))
           .andExpect(status().isOk());
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    void updateProduct(@Autowired MockMvc mvc) throws Exception {

        Category category = new Category();
        category.setCategoryid(1L);
        category.setName("Libri");
        Product product = new Product();
        product.setProductid(1L);
        product.setName("Da Basic a Java");
        product.setIsbn("8883780450");
        product.setCategory(category);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(product);

        mvc.perform(put("/api/products/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(requestJson))
           .andExpect(jsonPath("$.name", equalTo("Da Basic a Java")))
           .andExpect(status().isOk());
    }

    @Sql({ "/schema-h2.sql", "/data-h2.sql" })
    @Test
    void searchProduct() {

    }
}
