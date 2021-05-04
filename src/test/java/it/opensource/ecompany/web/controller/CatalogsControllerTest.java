package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogsControllerTest {

    @Autowired
    private CartBean cartBean;

    @Autowired
    MockMvc mvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                  .addFilters(springSecurityFilterChain)
                                  .build();
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllCategoriesTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/catalog/all").with(user("admin").password("admin")
                                                                .roles("ADMIN")))
           .andExpect(model().attribute("categories", hasSize(6)))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(view().name("catalog/categoriesList"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void createCategoryFormTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/admin/catalog").param("form", "")
                                         .with(user("admin.ecompany").password("admin")
                                                                     .roles("ADMIN")))
           .andExpect(view().name("catalog/categoriesList"))
           .andExpect(status().isOk());

    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
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

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void createCategoryErrorTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(post("/admin/catalog").param("form", "")
                                          .with(user("admin.ecompany").password("admin")
                                                                      .roles("ADMIN")))
           .andExpect(model().attribute("message", hasProperty("type", equalTo("error"))))
           .andExpect(view().name("catalog/categoriesList"))
           .andExpect(status().isOk());
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void deleteCategoryTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(delete("/admin/catalog/{categoryId}", 6L).with(user("admin.ecompany").password("admin")
                                                                                         .roles("ADMIN")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(5)))
           .andExpect(status().isOk());
    }
}
