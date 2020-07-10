package it.opensource.ecompany.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("html")
@AutoConfigureMockMvc
@SpringBootTest
public class CartControllerTest {

     @BeforeEach
    public void setUp() throws Exception {

    }

    @AfterEach
    public void tearDown() throws Exception {

    }

    @Test
    public void addProductToCartTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/cart/add/{productid}", 1L).header("referer", "/"))
           .andExpect(redirectedUrl("/"))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", hasProperty("numberProducts", equalTo(1))))
           .andExpect(request().sessionAttribute("scopedTarget.cartBean", hasProperty("subTotal", closeTo(29.90, 0.00001))));
    }

    @Test
    public void showCartTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/cart/show"))
           .andExpect(model().attribute("customer", notNullValue()))
           .andExpect(view().name("cart/show"));
    }

    @Test
    public void deleteCartTest(@Autowired MockMvc mvc) throws Exception {

    }
}
