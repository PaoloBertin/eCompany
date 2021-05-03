package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductsControllerTest {

    private MockMvc mvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    @BeforeEach
    public void setup() throws URISyntaxException {

        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac)
                                  .addFilters(this.springSecurityFilterChain)
                                  .build();
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewAllProductsPage1Test() throws Exception {

//        session.setAttribute("cartBean", cartBean);

//        MvcResult mvcResult = mvc.perform(get("/products").param("page", "0")
//                                                          .param("size", "10"))
//                                 .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
//                                 .andExpect(request().sessionAttribute("scopedTarget.cartBean", notNullValue()))
//           .andExpect(model().attributeExists("cartBean"))
//           .andExpect(model().attribute("cartBean", hasProperty("totalCost",  is(closeTo(BigDecimal.ZERO, BigDecimal.ZERO)))))
//                                 .andExpect(view().name("catalog/productsList"))
//                                 .andExpect(status().isOk())
//                                 .andReturn();

        // String xAuthToken = mvcResult.getResponse().getHeader(AuthenticationControllerV2.Params.SESSION_KEY);
//        MapSession curSession = (MapSession) sessionRepository.getSession(xAuthToken);
//        Assert.assertNotNull(curSession);


// I log in and then returns session
//        HttpSession session = mvc.perform(get("/products").param("page", "0")
        mvc.perform(get("/products").param("page", "0")
                                    .param("size", "10"))
//                                 .andDo(print())
//                                 .andExpect(status().isFound())
           .andExpect(model().attributeExists("cartBean"))
           .andExpect(model().attribute("cartBean", hasProperty("totalCost", is(closeTo(BigDecimal.ZERO, BigDecimal.ZERO)))))

           .andReturn()
           .getRequest()
           .getSession();
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewAllProductsPage3Test() throws Exception {

        mvc.perform(get("/products").param("page", "2")
                                    .param("size", "10"))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
//           .andExpect(model().attributeExists("cartBean"))
//           .andExpect(model().attribute("cartBean", hasProperty("totalCost", is(closeTo(BigDecimal.ZERO, BigDecimal.ZERO)))))
           .andExpect(view().name("catalog/productsList"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewProducstByCategoryByPage1Test() throws Exception {

        mvc.perform(get("/products/{categoryId}/all", 1L).param("page", "0")
                                                         .param("size", "10"))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
//           .andExpect(model().attributeExists("cartBean"))
//           .andExpect(model().attribute("cartBean", hasProperty("totalCost", is(closeTo(BigDecimal.ZERO, BigDecimal.ZERO)))))
           .andExpect(view().name("catalog/productsList"))
           .andExpect(status().isOk());
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void viewProducstByCategoryByPage2Test() throws Exception {

        mvc.perform(get("/products/{categoryId}/all", 1L).param("page", "1")
                                                         .param("size", "10"))
           .andExpect(model().attribute("products", hasProperty("content", hasSize(10))))
//           .andExpect(model().attributeExists("cartBean"))
//           .andExpect(model().attribute("cartBean", hasProperty("totalCost",  is(closeTo(BigDecimal.ZERO, BigDecimal.ZERO)))))
           .andExpect(view().name("catalog/productsList"))
           .andDo(print())
           .andExpect(status().isOk());
    }
}
