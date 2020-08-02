package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest
public class CartControllerTest {

    @Test
    public void addProductToCartTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/cart/add/{productid}", 1L).header("referer", "/"))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", hasProperty("numberProducts", equalTo(1))))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", hasProperty("subTotal", closeTo(29.90, 0.00001))))
           .andExpect(redirectedUrl("/"));
    }

    @Test
    public void showCartTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/cart/show").with(user("mario.rossi").password("user")
                                                                        .roles("USER")))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(view().name("cart/show"));
    }

    @Test
    public void deleteCartTest(@Autowired MockMvc mvc) throws Exception {

    }
}
