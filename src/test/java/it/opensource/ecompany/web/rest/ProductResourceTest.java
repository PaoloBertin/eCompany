package it.opensource.ecompany.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.web.form.SearchForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProductResourceTest {

    @Autowired
    private ProductsService productsService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllProductsByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/all").param("page", "0")
                                            .param("size", "10")
                                            .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.content.length()", equalTo(10)))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getProductsByCategory1ByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 1).param("page", "0")
                                                        .param("size", "10")
                                                        .contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.content.length()", equalTo(10)))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getProductsByCategory6ByPageTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/{categoryId}", 6).param("page", "0")
                                                        .param("size", "10")
                                                        .contentType(MediaType.APPLICATION_JSON))
           // .andDo(print())
           .andExpect(jsonPath("$.content.length()", equalTo(2)))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getProductByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/all/{productId}", 1L))
           .andExpect(jsonPath("$.name", equalTo("Da Visual Basic a Java")))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getPhotoByProductIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/products/photo/{productid}", 1L)).andDo(print()).andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
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

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void updateProductTest(@Autowired MockMvc mvc) throws Exception {

        Product product = productsService.getProductById(1L);
        product.setName("Da Basic a Java");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(product);

        mvc.perform(put("/api/products/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(requestJson))
           .andExpect(jsonPath("$.name", equalTo("Da Basic a Java")))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void searchProducTest(@Autowired MockMvc mvc) throws Exception {

        SearchForm searchForm = new SearchForm();
        searchForm.setTextToSearch("Java");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(searchForm);

        mvc
            .perform(get("/api/products/searchProduct").contentType(MediaType.APPLICATION_JSON)
                                                       .queryParam("page", "0")
                                                       .queryParam("size", "10")
                                                       .content(requestJson))
            .andExpect(jsonPath("$.content.length()", equalTo(6)))
            .andExpect(status().isOk());
    }
}
