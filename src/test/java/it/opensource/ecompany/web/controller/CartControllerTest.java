package it.opensource.ecompany.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // @Ignore
    @Test
    public void addProductToCart() {

    }

    // @Ignore
    @Test
    public void deleteProductFromToCart() {

    }

    // @Ignore
    @Test
    public void showCart() throws Exception {

        mvc
            .perform(get("/cart/show"))
            .andExpect(model().attribute("customer", notNullValue()))
            .andExpect(view().name("cart/show"));
    }

    // @Ignore
    @Test
    public void deleteCart() {

    }
}
