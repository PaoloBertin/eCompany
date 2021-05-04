package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CartControllerTest {

    private MockHttpSession mockHttpSession;

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

        mockHttpSession = new MockHttpSession();
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                             .addFilters(springSecurityFilterChain)
                             .build();
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void addProductToCartTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/cart/add/{productid}", 1L).header("referer", "/"))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", hasProperty("numberProducts", equalTo(1))))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", hasProperty("subTotal", closeTo(29.90, 0.0001))))
           .andExpect(redirectedUrl("/"));
    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void showCartTest(@Autowired MockMvc mvc) throws Exception {

        mockHttpSession.setAttribute("cartBeab", cartBean);
        mvc.perform(get("/cart/show").with(user("mario.rossi").password("user")
                                                              .roles("USER")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(view().name("cart/show"));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void deleteCartTest(@Autowired MockMvc mvc) throws Exception {

    }

}
