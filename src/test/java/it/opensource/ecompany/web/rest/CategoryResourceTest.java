package it.opensource.ecompany.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CategoryResourceTest {

    @Autowired
    CategoriesService categoriesService;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllCategoriesTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.length()", equalTo(6)))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getCategoryByIdTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/api/categories/{categoryId}", 1L).contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.id", equalTo(1)))
           .andExpect(jsonPath("$.name", equalTo("Libri")))
           .andExpect(status().isOk());

    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void createCategoryTest(@Autowired MockMvc mvc) throws Exception {

        Category category = new Category("eBook");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(category);

        mvc.perform(post("/api/categories").contentType(MediaType.APPLICATION_JSON)
                                           .content(requestJson))
           .andExpect(jsonPath("$.id", equalTo(7)))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void deleteCategoryTest(@Autowired MockMvc mvc) throws Exception {

        Category category = new Category(7L, "Arredamento");
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestJson = objectMapper.writeValueAsString(category);

        // aggiunge categoria al db e verifica l'aggiunta
        mvc.perform(post("/api/categories").contentType(MediaType.APPLICATION_JSON)
                                           .content(requestJson))
           .andExpect(jsonPath("$.id", equalTo(7)))
           .andExpect(status().isOk());

        // verifica che il numero di categoria sia accresciuto di una unita'
        mvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.length()", equalTo(7)))
           .andExpect(status().isOk());

        category = categoriesService.getCategoryByName("Arredamento");
        objectMapper = new ObjectMapper();
        requestJson = objectMapper.writeValueAsString(category);

        // elimina categoria dal db
        mvc.perform(delete("/api/categories").contentType(MediaType.APPLICATION_JSON)
                                             .content(requestJson))
           .andExpect(status().isOk());

        // verifica che il numero di categoria sia accresciuto di una unita'
        mvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.length()", equalTo(6)))
           .andExpect(status().isOk());

    }
}
