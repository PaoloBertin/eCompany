package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@EnableWebMvc
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovementsControllerTest {

    @Autowired
    CartBean cartBean = new CartBean();

    @Autowired
    MockHttpSession session;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void viewMovements() throws Exception {

        mvc
            .perform(get("/movements/all/customers/2/checkout")
                .with(user("mario.rossi").password("user").roles("USER"))
            )
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Books")))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalAmount", equalTo(0.0F))))
            .andExpect(view().name("movements/checkout"))
            .andExpect(status().isOk());
    }

    @Test
    public void getAllMovements() throws Exception {

        mvc
            .perform(get("/movements/all")
                .with(user("mario.rossi").password("user").roles("USER"))
            )
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Books")))))
            .andExpect(model().attribute("movements", IsCollectionWithSize.hasSize(10)))
            .andExpect(model().attribute("movements", hasItem(hasProperty("totalamount", equalTo(169.5)))))
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalAmount", equalTo(0.0F))))
            .andExpect(view().name("movements/list"))
            .andExpect(status().isOk());

    }

    @Test
    public void getMovementById() throws Exception {

        mvc
            .perform(get("/movements/{id}", 2L).sessionAttr("cartBean", cartBean)
                .with(user("mario.rossi").password("user").roles("USER"))
            )
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalAmount", equalTo(0.0F))))
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Books")))))
            .andExpect(model().attribute("movement", hasProperty("totalamount", equalTo(49.90))))
            .andExpect(view().name("movements/show"))
            .andExpect(status().isOk());
    }

    @Test
    public void getMovementsByCustomer() throws Exception {

        mvc
            .perform(get("/movements/all/customers/{customerId}", 2L)
                .with(user("mario.rossi").password("user").roles("USER"))
            )
//            .andDo(print())
            .andExpect(model().attributeExists("cartBean"))
            .andExpect(model().attribute("cartBean", hasProperty("totalAmount", equalTo(0.0F))))
            .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(6)))
            .andExpect(model().attribute("categories", hasItem(hasProperty("name", is("Books")))))
            .andExpect(model().attribute("movements", IsCollectionWithSize.hasSize(4)))
            .andExpect(model().attribute("movements", hasItem(hasProperty("customer", is(hasProperty("username", equalTo("mario.rossi")))))))
            .andExpect(view().name("movements/list"))
            .andExpect(status().isOk());
    }

}